import {Address} from "./address";

export interface SportClubEvent {
  responsibles?: [any],
  enrollments?: [any],
  imageUrl?: string,
  address?: Address;
  startDate?: Date;
  endDate?: Date;
  deadline?:Date;
  closed?:boolean;
  price?;
  minParticipants?: number;
  maxParticipants?: number;
  description?: string;
  name:string;
}
