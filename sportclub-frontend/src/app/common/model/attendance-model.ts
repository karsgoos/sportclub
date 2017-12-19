import {Order} from "./order-model";

export interface AttendanceModel {

  id: number;
  price: number;
  description: String;
  ordr: Order;
  ageCategory: String;
  cancelled: boolean;

}
