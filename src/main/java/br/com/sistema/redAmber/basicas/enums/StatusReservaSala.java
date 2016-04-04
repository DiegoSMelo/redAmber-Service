package br.com.sistema.redAmber.basicas.enums;

public enum StatusReservaSala {

	P {
		@Override
		public String toString() {
			return "Pendente";
		}
	},
	A {
		@Override
		public String toString() {
			return "Aprovada";
		}
	},
	N {
		@Override
		public String toString() {
			return "Negada";
		}
	},
	C {
		@Override
		public String toString() {
			return "Cancelada";
		}
	}
}