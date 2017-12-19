/*
Ugly method to convert a string that represents a date like it is coming from the used datepickers, to a string that
can be recognized in our backend api.
 */
export function convertDateString(input:string):string{
  let temp = input.split(" ");
  let day: string = temp[0];
  if(day.length===1){
    day = "0" + day;
  }
  let year: string = temp[2];
  let month: string;
  switch (temp[1]) {
    case "Januari,":
      month = "01";
      break;
    case "Februari,":
      month = "02";
      break;
    case "Maart,":
      month = "03";
      break;
    case "April,":
      month = "04";
      break;
    case "Mei,":
      month = "05";
      break;
    case "Juni,":
      month = "06";
      break;
    case "Juli,":
      month = "07";
      break;
    case "Augustus,":
      month = "08";
      break;
    case "September,":
      month = "09";
      break;
    case "Oktober,":
      month = "10";
      break;
    case "November,":
      month = "11";
      break;
    case "December,":
      month = "12";
      break;
    default:
      month = "00";
  }

  return year + '/' + month + '/' + day;
}

export function dateToPickerString(date: Date): string {
  const monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  ];

  let value = String(date.getDate()) + ' ' + monthNames[date.getMonth()] + ', ' + date.getFullYear();
  return value.trim();
}

export function timeToPickerString(date: Date): string {
  let hoursString = date.getHours() < 10 ? '0' + date.getHours() : String(date.getHours());
  let minutesString = date.getMinutes() < 10 ? '0' + date.getMinutes() : String(date.getMinutes());
  let value = String(hoursString + ':' + minutesString);
  return value.trim();
}
