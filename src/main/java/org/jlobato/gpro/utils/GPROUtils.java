package org.jlobato.gpro.utils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.Optional;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * 
 * @author jlobato
 *
 */
public class GPROUtils {
	
	private static final DecimalFormat decimalFormat = new DecimalFormat("$###,###,###,###;$-###,###,###,###", new DecimalFormatSymbols(new Locale("es")));
	
	private GPROUtils() {
		
	}

	/**
	 * 
	 * @param lapTime
	 * @return
	 */
	public static Integer getMiliseconds(String lapTime) {
		Integer result = null;
		String[] milis = lapTime.split("\\.");
		//Si el array no tiene dos elementos es que es nulo
		if (milis.length > 1) {
			String parteEntera = milis[0];
			String parteDecimal = milis[1];
			String[] partesEnteras = parteEntera.split("\\:");
			int acum = Integer.parseInt(parteDecimal);
			int index = 0;
			for (int i = partesEnteras.length - 1; i >= 0; i--) {
				acum += Integer.parseInt(partesEnteras[i]) * timeFactors[index];
				index++;
			}
			result = Integer.valueOf(acum);
		}
		return result;
	}
	
	/**
	 * 
	 * @param lapTime
	 * @return
	 */
	public static Integer getMilis(String lapTime) {
		Integer result = null;
		try {
			PeriodFormatter periodFormat = new PeriodFormatterBuilder().minimumPrintedDigits(2).appendMinutes()
					.appendSeparator(":").minimumPrintedDigits(2).appendSeconds().appendSeparator(".")
					.appendMillis3Digit().toFormatter();
			Period period = Period.parse(lapTime, periodFormat);
			result = Integer.valueOf(((int) period.toStandardDuration().getMillis()));
		} catch (Exception e) {
			// Nos callamos como ...
		}
		return result;
	}
	
	/**
	 * 
	 * @param pitInfo
	 * @return
	 */
	public static Integer getPitNo(String pitInfo) {
		int index1 = pitInfo.indexOf("Stop") + "Stop".length();
		int index2 = pitInfo.indexOf("(");
		String lapNo = excellTrim(pitInfo.substring(index1, index2));
		return Integer.valueOf(lapNo.trim());
	}
	
	/**
	 * 
	 * @param excelSting
	 * @return
	 */
	public static String excellTrim(String excelSting) {
		return excelSting.replaceAll("\\u00A0", "");
	}

	/**
	 * 
	 */
	protected static int[] timeFactors = {1000, 60 * 1000, 60 * 60 * 1000};

	/**
	 * 
	 * @param numero
	 * @param decimales
	 * @return
	 */
	public static double redondeo(double numero, int decimales) {
		double result = 0.0;
		try {
			String valor = Double.toString(numero);
			BigDecimal big = new BigDecimal(valor);
			big = big.setScale(decimales, RoundingMode.HALF_UP);
			result = big.doubleValue();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @param linkUrl
	 * @return
	 */
	public static String getIDManagerFromLink(String linkUrl) {
		String result = "";
		int index = linkUrl.indexOf("IDM") + "IDM".length() + 1;
		result = linkUrl.substring(index, linkUrl.length());
		return result;
	}
	
	/**
	 * 
	 * @param group
	 * @return
	 */
	public static String getCategoryCode(String group) {
		String result = "";
		int index = group.indexOf("-");
		result = (index >= 0) ? group.substring(0, index) : group.equals("Ret") ? "-" : "E"; //En el caso de que no hay guión, siempre es Elite		
		return result;
	}
	
	/**
	 * 
	 * @param group
	 * @return
	 */
	public static String getGroupId(String group) {
		String result = null;
		
		int index = group.indexOf("-");
		if (index > 0) result = group.substring(index + 1, group.length());
		
		return result;
	}
	
	/**
	 * 
	 * @param value
	 * @param clazz
	 * @return
	 */
	public static <T> T castIfNotNull(Object value, Class<T>clazz) {
		T result = null;
		if (value != null) {
			try {
				result = clazz.getConstructor(value.getClass()).newInstance(value);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				return null;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String getTyreBrandCode(String url) {
		Optional<String> result = Optional.ofNullable(url);
		if (result.isPresent()) {
			if (result.get().contains("avonn")) return "AV";
			else if (result.get().contains("pipirelli")) return "PI";
			else if (result.get().contains("dunnolop")) return "DU";
			else if (result.get().contains("yokomama")) return "YO";
			else if (result.get().contains("hancock")) return "HA";
			else if (result.get().contains("michelini")) return "MI";
			else if (result.get().contains("contimental")) return "CO";
			else if (result.get().contains("bridgerock")) return "BR";
			else if (result.get().contains("badyear")) return "BY";
			else return null;
		}
		else return null;
	}
	
	/**
	 * 
	 * @param driverEnergyValue
	 * @return
	 */
	public static int getDriverEnergyAtStart(String driverEnergyValue) {
		int firstPercentIndex = driverEnergyValue.indexOf("%");
		return Integer.parseInt(driverEnergyValue.substring(0, firstPercentIndex));
	}
	
	/**
	 * 
	 * @param driverEnergyValue
	 * @return
	 */
	public static int getDriverEnergyAtEnd(String driverEnergyValue) {
		//Metemos +2 porque el espacio no se limpia con trim (no se muy bien porque)
		int separatorIndex = driverEnergyValue.indexOf(">") + 2;
		int lastPercentIndex = driverEnergyValue.lastIndexOf("%");
		return Integer.parseInt(driverEnergyValue.substring(separatorIndex, lastPercentIndex));
	}

	/**
	 * 
	 * @param pitTime
	 * @return
	 */
	public static int getPitTimeMillis(String pitTime) {
		String[] pitTimeParts = pitTime.split("\\.");
		return (Integer.parseInt(pitTimeParts[0]) * 1000) + Integer.parseInt(pitTimeParts[1]);
	}

	/**
	 * 
	 * @param moneyBalance
	 * @return
	 */
	public static Integer getMoneyAsInt(String moneyBalance) {
		Integer result = null;
		try {
			result = decimalFormat.parse(moneyBalance).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getIDSeason(String seasonAsString) {
		if (seasonAsString == null) return null;
		int commaIndex = seasonAsString.indexOf(",");
		int spaceIndex = seasonAsString.indexOf(" ") + 1;
		return seasonAsString.substring(spaceIndex, commaIndex);
	}
	
	public static String getIDRace(String seasonAsString) {
		if (seasonAsString == null) return null;
		int spaceIndex = seasonAsString.lastIndexOf(" ") + 1;
		return seasonAsString.substring(spaceIndex);
	}
	
	
	public static String convertDate(String dateGproFormat) {
		DateTimeFormatter inputFormatter = new DateTimeFormatterBuilder()
				.parseCaseInsensitive()
				.appendPattern("MMM d['th']['nd']['st']['rd'], yyyy")
				.toFormatter(Locale.ENGLISH);
        
        // Definir el formato de salida
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Parsear la fecha de entrada
        LocalDate date = LocalDate.parse(dateGproFormat, inputFormatter);
        
        // Formatear la fecha con el formato deseado
        String formattedDate = date.format(outputFormatter);
        
        return formattedDate;
	}
	
}
