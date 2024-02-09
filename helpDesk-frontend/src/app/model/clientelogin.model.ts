import { CargoId } from "./cargoId.model";

export interface ClienteLogin {
  id?: any;
  nome: string;
  email: string;
  senha: string;
  cargos: CargoId[];
}