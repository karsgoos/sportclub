import {Address} from "./address";
import {RecurringEventInfo} from "./sportclub-recuring-event-info";

export interface SportClubCreationEvent {
  id?,
  responsibles?,
  enrollments?,
  imageUrl?: string,
  address?: Address;
  startDate?: string;
  endDate?: string;
  deadline?:string;
  closed?:boolean;
  priceChild?:number,
  priceAdult?:number,
  minParticipants?: number;
  maxParticipants?: number;
  description?: string;
  name?:string;
  recurringEventInfo?: RecurringEventInfo;
  reminderDate?: string;
}
