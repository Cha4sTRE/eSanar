package cj.esanar.util.reports;

import cj.esanar.persistence.entity.PacienteEntity;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class ExportarPacientesExel {

    private XSSFWorkbook libro;
    private XSSFSheet hoja;
    private List<PacienteEntity> pacientes;

    public ExportarPacientesExel(List<PacienteEntity> pacientes) {
        this.pacientes = pacientes;
        libro= new XSSFWorkbook();
        hoja= libro.createSheet("Pacientes");
    }

    private void headerSheet(){
        Row fila= hoja.createRow(0);
        CellStyle style= libro.createCellStyle();
        XSSFFont font = libro.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);

        //celdas
        Cell cell=fila.createCell(0);
        cell.setCellValue("Id");
        cell.setCellStyle(style);

        cell=fila.createCell(1);
        cell.setCellValue("Nombre");
        cell.setCellStyle(style);

        cell=fila.createCell(2);
        cell.setCellValue("Apellido");
        cell.setCellStyle(style);

        cell=fila.createCell(3);
        cell.setCellValue("Tipo de Documento");
        cell.setCellStyle(style);

        cell=fila.createCell(4);
        cell.setCellValue("Identifícacion");
        cell.setCellStyle(style);

        cell=fila.createCell(5);
        cell.setCellValue("teléfono");
        cell.setCellStyle(style);

        cell=fila.createCell(6);
        cell.setCellValue("Direccíon");
        cell.setCellStyle(style);

        cell=fila.createCell(7);
        cell.setCellValue("Barrio");
        cell.setCellStyle(style);

        cell=fila.createCell(8);
        cell.setCellValue("fecha de nacimiento");
        cell.setCellStyle(style);

        cell=fila.createCell(9);
        cell.setCellValue("Edad");
        cell.setCellStyle(style);

        cell=fila.createCell(10);
        cell.setCellValue("Sexo");
        cell.setCellStyle(style);

        cell=fila.createCell(11);
        cell.setCellValue("Tipo de Sangre");
        cell.setCellStyle(style);

        cell=fila.createCell(12);
        cell.setCellValue("Correo");
        cell.setCellStyle(style);

        cell=fila.createCell(13);
        cell.setCellValue("Ocupacíon");
        cell.setCellStyle(style);

        cell=fila.createCell(14);
        cell.setCellValue("Entidad");
        cell.setCellStyle(style);

        cell=fila.createCell(15);
        cell.setCellValue("Estado Civil");
        cell.setCellStyle(style);

    }

    private void escribirDatos(){

        int filas=1;
        CellStyle style= libro.createCellStyle();
        XSSFFont font= libro.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        for(PacienteEntity paciente: pacientes){
            Row fila= hoja.createRow(filas++);

            Cell cell=fila.createCell(0);
            cell.setCellValue(paciente.getId());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(0);

            cell=fila.createCell(1);
            cell.setCellValue(paciente.getNombre());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(1);

            cell=fila.createCell(2);
            cell.setCellValue(paciente.getApellido());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(2);

            cell=fila.createCell(3);
            cell.setCellValue(paciente.getTipoDocumento());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(3);

            cell=fila.createCell(4);
            cell.setCellValue(paciente.getIdentificacion());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(4);

            cell=fila.createCell(5);
            cell.setCellValue(paciente.getTelefono());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(5);

            cell=fila.createCell(6);
            cell.setCellValue(paciente.getDireccion());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(6);

            cell=fila.createCell(7);
            cell.setCellValue(paciente.getBarrio());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(7);

            cell=fila.createCell(8);
            cell.setCellValue(paciente.getFechaNacimiento());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(8);

            cell=fila.createCell(9);
            cell.setCellValue(paciente.getEdad());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(9);

            cell=fila.createCell(10);
            cell.setCellValue(paciente.getSexo());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(10);

            cell=fila.createCell(11);
            cell.setCellValue(paciente.getTipoSangre());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(11);

            cell=fila.createCell(12);
            cell.setCellValue(paciente.getCorreo());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(10);

            cell=fila.createCell(13);
            cell.setCellValue(paciente.getOcupacion());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(13);

            cell=fila.createCell(14);
            cell.setCellValue(paciente.getEntidad());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(14);

            cell=fila.createCell(15);
            cell.setCellValue(paciente.getEstadoCivil());
            cell.setCellStyle(style);
            hoja.autoSizeColumn(15);
        }

    }

    public void exportar(HttpServletResponse response) throws IOException {
        headerSheet();
        escribirDatos();

        ServletOutputStream outputStream= response.getOutputStream();
        libro.write(outputStream);
        libro.close();

    }

}
