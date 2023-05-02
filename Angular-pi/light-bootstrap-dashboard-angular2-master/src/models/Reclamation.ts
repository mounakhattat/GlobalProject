import { StateReclamation } from "./enums/StateReclamation";
import { TypeReclamation } from "./enums/TypeReclamation";

export class Reclamation {
    idReclamation:number;
    title:string;
    content:string;
    dateReclamation:Date;
    state:StateReclamation;
    type:TypeReclamation;

}