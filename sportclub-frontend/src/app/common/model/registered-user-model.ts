import {User} from "./user-model";
import {Address} from "./address-model";
import {Enrollment} from "./enrollment-model";

export interface RegisteredUser extends User {

  dateOfBirth: Date;
  gender: String;
  address: Address;
  phoneNumber: String;
  mobileNumber: String;
  password: String;
  childAccounts: Array<User>;
  enrollments: Array<Enrollment>;
  totalPoints: number;

}
