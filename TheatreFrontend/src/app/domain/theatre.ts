import { ScreenLayout } from "./screenLayout";

export class Theatre {
    constructor(
      public theatreId: number,
      public theatreName: string,
      public theatreCity: string,
      public theatreAddress: string,
      public screenLayout: ScreenLayout
    ) {}
  }