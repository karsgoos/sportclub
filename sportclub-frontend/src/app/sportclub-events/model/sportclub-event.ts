import {Address} from "./address";

export interface SportClubEvent {
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
}
