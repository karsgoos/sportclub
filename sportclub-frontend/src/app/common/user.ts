export class User {
  private _email: string;
  private _points: number;

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get points(): number {
    return this._points;
  }

  set points(value: number) {
    this._points = value;
  }
}
