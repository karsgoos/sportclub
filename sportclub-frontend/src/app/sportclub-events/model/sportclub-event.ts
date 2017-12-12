import {Address} from "./address";

export interface SportClubEvent {
  address?: Address;
  startDate?: Date;
  endDate?: Date;
  deadline?:Date;
  isClosed?:boolean;
  price?;
  minParticipants?: number;
  maxParticipants?: number;
  description?: string;
  name:string;
}
