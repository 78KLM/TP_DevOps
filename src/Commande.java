import java.time.LocalDateTime;

// Classe de commande créée par le service
public record Commande(String identifiantClient, double total, LocalDateTime dateCreation) {}