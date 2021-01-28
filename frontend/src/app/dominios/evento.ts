export class Evento{
    id: number;
    titulo: string;
    dataInicio: Date;
    dataFim: Date;
    descricao: string;
    quantVagas: number;
    valor: number;
    local: string;
    tipoInscricao: boolean;
    idTipoEvento: number;
    perguntas: any[]
}