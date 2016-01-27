package com.fagnerng.m3v.entity;

public class Tree {
	private long indentificacao;
	private String especie;
	private double latitude;
	private double longitude;
	private double fuste;
	private double altura;
	private double dap;
	private double cap;
	private boolean biologica;
	private boolean antropica;
	private String foto;
	
	public long getIndentificacao() {
		return indentificacao;
	}
	public void setIndentificacao(long indentificacao) {
		this.indentificacao = indentificacao;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getFuste() {
		return fuste;
	}
	public void setFuste(double fuste) {
		this.fuste = fuste;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getDap() {
		return dap;
	}
	public void setDap(double dap) {
		this.dap = dap;
	}
	public double getCap() {
		return cap;
	}
	public void setCap(double cap) {
		this.cap = cap;
	}
	public boolean isBiologica() {
		return biologica;
	}
	public void setBiologica(boolean biologica) {
		this.biologica = biologica;
	}
	public boolean isAntropica() {
		return antropica;
	}
	public void setAntropica(boolean antropica) {
		this.antropica = antropica;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
}
