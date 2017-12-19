import {RegisteredUser} from "./registered-user-model";
import {Enrollment} from "./enrollment-model";
import {Address} from "./address-model";
import {RecurringEventInfo} from "./recurring-event-info";
import {AttendanceModel} from "./attendance-model";
export interface SportClubEvent {

  id: number;
  responsibles: Array<RegisteredUser>;
  enrollments: Array<Enrollment>;
  imageMimeType: string;
  startDate: Date;
  endDate: Date;
  address: Address;
  deadline: Date;
  priceAdult: number;
  priceChild: number;
  minParticipants: number;
  maxParticipants: number;
  description : String;
  name: String;
  recurringEventInfo: RecurringEventInfo;
  closed: boolean;
  points: number;
  attendancies: Array<AttendanceModel>;
}
