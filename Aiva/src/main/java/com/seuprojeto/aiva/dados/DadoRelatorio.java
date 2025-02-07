    package com.seuprojeto.aiva.dados;

    public class DadoRelatorio {
        private String descricao;
        private double valor;
        private String categoria;
        private String tipo; // "Venda" ou "Despesa"
        private String data; // ✅ Novo campo para a data

        public DadoRelatorio(String descricao, double valor, String categoria, String tipo, String data) {
            this.descricao = descricao;
            this.valor = valor;
            this.categoria = categoria;
            this.tipo = tipo;
            this.data = data; // ✅ Adiciona a data
        }

        public String getDescricao() { return descricao; }
        public double getValor() { return valor; }
        public String getCategoria() { return categoria; }
        public String getTipo() { return tipo; }
        public String getData() { return data; } // ✅ Getter para a data
    }
