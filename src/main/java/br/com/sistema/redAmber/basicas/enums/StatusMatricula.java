package br.com.sistema.redAmber.basicas.enums;

public enum StatusMatricula {
	ATIVO {
		@Override
		public String toString() {

			return "Ativo";
		}
	},
	INATIVO {
		@Override
		public String toString() {

			return "Inativo";
		}
	}
}
