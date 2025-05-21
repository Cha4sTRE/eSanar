package cj.esanar.util.reports;

import cj.esanar.persistence.entity.ConsultaEntity;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.awt.*;
import java.io.IOException;

@AllArgsConstructor
public class ExportarConsultaPdf {

    ConsultaEntity consulta;

    private void header(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GREEN);
        cell.setPadding(5);

        Font font= FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Campo",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Valor",font));
        table.addCell(cell);
    }

    private void body(PdfPTable table) {

        table.addCell("Paciente");
        table.addCell(consulta.getHistoriaClinica().getPaciente().getNombre()+" "+consulta.getHistoriaClinica().getPaciente().getApellido());
        table.addCell("Motivo");
        table.addCell(consulta.getMotivoConsulta());
        table.addCell("Diagnostico(CIE-11)");
        table.addCell(consulta.getDiagnosticoPrincipal());
        table.addCell("infeccion");
        table.addCell(consulta.getInfeccion());
        table.addCell("Forma");
        table.addCell(consulta.getForma());
        table.addCell("Largo");
        table.addCell(String.valueOf(consulta.getLargo()));
        table.addCell("Ancho");
        table.addCell(String.valueOf(consulta.getAncho()));
        table.addCell("Profundidad");
        table.addCell(String.valueOf(consulta.getProfundidad()));
        table.addCell("Olor");
        table.addCell(consulta.getOlor());
        table.addCell("Bordes");
        table.addCell(consulta.getBordesHerida());
        table.addCell("Tipo de Exudado");
        table.addCell(consulta.getExudadoTipo());
        table.addCell("Nivel de Exudado");
        table.addCell(consulta.getExudadoNivel());

    }

    public void export(HttpServletResponse response) throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(22);

        Paragraph titulo = new Paragraph("Reporte de Consulta para "+consulta.getHistoriaClinica().getPaciente().getNombre()+
                " "+consulta.getHistoriaClinica().getPaciente().getApellido(), font);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{4f,4f});
        header(table);
        body(table);

        document.add(table);
        document.close();

    }

}
