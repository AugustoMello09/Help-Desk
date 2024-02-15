import { ClienteId } from "./clienteId.model";
import { TecnicoId } from "./tecnicoId.model";

export interface Chamado {
  id?: any;
  dataAberto: string;
  descricao: string;
  dataFechamento: string;
  statusChamado: string;
  tecnico: TecnicoId;
  cliente: ClienteId;
}