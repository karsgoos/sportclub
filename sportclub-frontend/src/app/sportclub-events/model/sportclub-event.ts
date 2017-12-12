export interface SportClubEvent {
  recurring: boolean;

  name: string;
  price1: number;
  price2: number;
  duration: number;
  startDate: Date;
  open: boolean;
  location: string;
}
