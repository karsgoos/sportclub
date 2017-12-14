
//String enum weekday
class Weekday {
  static MONDAY = "MONDAY";
  static TUESDAY = "TUESDAY";
  static WEDNESDAY = "WENDNESDAY";
  static THURSDAY = "THURSDAY";
  static FRIDAY = "FRIDAY";
  static SATURDAY = "SATURDAY";
  static SUNDAY = "SUNDAY";

}
export interface RecuringEventInfo{
  startDate: Date,
  endDate: Date,
  weekdays: [Weekday]
}
