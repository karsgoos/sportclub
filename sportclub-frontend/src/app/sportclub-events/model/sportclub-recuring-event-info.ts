
//String enum weekday
export class Weekday {
  static MONDAY = "MONDAY";
  static TUESDAY = "TUESDAY";
  static WEDNESDAY = "WENDNESDAY";
  static THURSDAY = "THURSDAY";
  static FRIDAY = "FRIDAY";
  static SATURDAY = "SATURDAY";
  static SUNDAY = "SUNDAY";

}
export interface RecurringEventInfo{
  startDate: Date,
  endDate: Date,
  weekdays: [string]
}
