package co.com.sofkau.modelos;

public class Correos {
    private String correo;

    public Correos(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDominio(){
        try {
            String dominio = this.getCorreo().split("@")[1];
            return dominio;
        } catch (Exception e) {
            return "No tiene dominio";
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((correo == null) ? 0 : correo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Correos other = (Correos) obj;
        if (correo == null) {
            if (other.correo != null)
                return false;
        } else if (!correo.equals(other.correo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Correo = " + correo;
    }
}
