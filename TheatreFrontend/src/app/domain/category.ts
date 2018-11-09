import { SeatLayout } from "./seatLayout";

export class Category {
    constructor(
      public type: string,
      public noOfColumns: number,
      public noOfRows: number,
      public seatLayout: SeatLayout[]
    ) {}
  }