package br.com.sistema.redAmber.basicas.enums;

public enum StatusDisciplina {
	ATIVA {
		@Override
		public String toString() {
			return "Ativa";
		}
	},
	INATIVA {
		@Override
		public String toString() {
			return "Inativa";
		}
	}
}