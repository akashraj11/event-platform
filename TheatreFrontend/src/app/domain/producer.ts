import { Theatre } from "./theatre";

export class Producer {
    constructor(
      public email: string,
      public theatres: Theatre[]
    ) {}
  }