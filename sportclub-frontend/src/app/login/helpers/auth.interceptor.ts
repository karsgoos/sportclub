import {Injectable, Injector} from '@angular/core';
import {HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import {AuthenticationService} from '../services';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private inj: Injector;

  constructor(private auth: AuthenticationService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // NICO: I want to fetch the authentication service for calling functions, but this implementation is currently not working
    // NICO: and gives cyclic dependency error
    const authService = this.inj.get(AuthenticationService);

    // add authorization header with jwt token if available
    const user = JSON.parse(localStorage.getItem('user'));

    // TODO: if structuur herbekijken
    // NICO: I'm doing a check to see what kind of request we're getting
    // NICO: Checking if user is set
    if (!user) {
      // NICO: Checking if the request is meant to fetch an authentication token
      if (request.url.endsWith('/oauth/token')) {
        request = request.clone({
          setHeaders: {
            'Authorization': `Basic ` + btoa('angular:secret'),
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        });

      }
      // NICO: checking the request URL for api calls that don't require authorization (e.g. 'api/register')
      else if (request.url.endsWith('/register')) {
        // TODO: do something when guest performs register on site or to event
      }
      // NICO: if none of above apply -> throws Forbidden response error
      else {
        throw new HttpErrorResponse(403);
      }
    }
    // NICO: go into this check state when user is not set
    else {
      const access_token = localStorage.getItem('access_token');

      // NICO: check if access token is set
      if (access_token) {
        request = request.clone({
          setHeaders: {
            Authorization: `Bearer ${access_token}`
          }
        });
      }
      // NICO: does 'else' when access token is NOT set
      else {
        // NICO: if request token is NOT set and URL is for fetching new access token -> set Authorization header for fetching new access token
        if (request.url.endsWith('/oauth/token')) {
          request = request.clone({
            setHeaders: {
              Authorization: `Basic ` + btoa('angular:secret')
            }
          });
        }
        // NICO: if none of above apply -> throws Forbidden response error
        else {
          throw new HttpErrorResponse(403);
        }
      }
    }

    return next.handle(request)
      .map((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse && ~(event.status / 100) > 3) {
          console.info('HttpResponse::event =', event, ';');
        } else console.info('event =', event, ';');
        return event;
      })
      .catch((err: any, caught) => {
        if (err instanceof HttpErrorResponse) {
          var refresh_token = localStorage.getItem('refresh_token');

          // NICO: If response gives 401 error, try getting new access token
          if (err.status === 401 && refresh_token) {
            authService.refresh();
          }

          return Observable.throw(err);
        }
      })
  }
}
