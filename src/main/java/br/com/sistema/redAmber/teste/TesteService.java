package br.com.sistema.redAmber.teste;

import java.util.Calendar;

import br.com.sistema.redAmber.util.Datas;

public class TesteService {

	public static void main(String[] args) {
		
		
		String arg2 = "18/05/2016";
		
		String[] dataSplit = arg2.split("/");
		
		Calendar c = Datas.converterDateToCalendar(Datas.criarData(Integer.
				parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2])));
		Long data = c.getTimeInMillis();
		System.err.println("DATA EM MILISEGUNDOS: " + data);		
	}
}