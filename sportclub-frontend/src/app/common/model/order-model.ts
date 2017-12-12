import {Orderable} from "./orderable-model";
import {RegisteredUser} from "./registered-user-model";

export interface Order {

  id: number;
  isPaid: boolean;
  orderables: Array<Orderable>
  price: number;
  registeredUser: RegisteredUser;

}
