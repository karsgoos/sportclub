import {RegisteredUser} from "./registered-user-model";
import {Enrollment} from "./enrollment-model";
import {Address} from "./address-model";
import {PriceModel} from "./price-model";
import {RecurringEventInfo} from "./recurring-event-info";
import {AttendingModalComponent} from "../../sportclub-events/attending-modal/attending-modal.component";
export interface SportClubEvent {

  id: number;
  responsibles: Array<RegisteredUser>;
  enrollments: Array<Enrollment>;
  imageUrl: String;
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
  attendencies: Array<AttendingModalComponent>;
}
