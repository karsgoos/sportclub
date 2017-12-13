import {RegisteredUser} from "./registered-user-model";
import {Enrollment} from "./enrollment-model";
import {Address} from "./address-model";
export interface SportClubEvent {

  id: number;
  responsibles: Array<RegisteredUser>;
  enrollments: Array<Enrollment>;
  imageUrl: String;
  startDate: Date;
  endDate: Date;
  address: Address;
  deadline: Date;
  isClosed: Date;
  price: Array<number>;
  minParticipants: number;
  maxParticipants: number;
  description : String;
  title: String;
  recurringEventId: String;

}
