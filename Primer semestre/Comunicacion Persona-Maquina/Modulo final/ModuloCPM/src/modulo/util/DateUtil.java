package modulo.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtil {
	public static String getFechaConFormato(String fecha, Locale localizacion){
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");
			DateFormat formatoFecha2 = DateFormat.getDateInstance(DateFormat.LONG, localizacion);
			
			return formatoFecha2.format(formatoFecha.parse(fecha));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static Date getFecha(String fecha){
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");
			
			return formatoFecha.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getFechaSinFormato(String fecha, Locale localizacion){
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy", localizacion);
			DateFormat formatoFecha2 = DateFormat.getDateInstance(DateFormat.LONG, localizacion);
			
			return formatoFecha.format(formatoFecha2.parse(fecha));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
}
