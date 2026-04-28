package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Maneja los fondos del juego.
 * Carga las imágenes una sola vez y las guarda en memoria
 * para redibujarlas rápido sin afectar rendimiento.
 */
public class BackgroundManager {

    // Lista de nombres de fondos disponibles (los que tengas en /resources/backgrounds/)
    public static final String[] BACKGROUND_NAMES = {
        "Bogotá",
        "Selva",
        "Cafetero"
    };

    // Nombres de los archivos correspondientes
    private static final String[] BACKGROUND_FILES = {
        "/backgrounds/bogota.png",
        "/backgrounds/selva.png",
        "/backgrounds/cafetero.png"
    };

    // Imágenes cargadas en memoria
    private BufferedImage[] images;

    // Índice del fondo actualmente seleccionado
    private int currentIndex = 0;

    public BackgroundManager() {
        images = new BufferedImage[BACKGROUND_FILES.length];

        // Carga todas las imágenes al inicio (solo una vez)
        for (int i = 0; i < BACKGROUND_FILES.length; i++) {
            try {
                images[i] = ImageIO.read(
                    getClass().getResourceAsStream(BACKGROUND_FILES[i])
                );
            } catch (IOException | IllegalArgumentException e) {
                // Si no encuentra la imagen, deja null (se usará el fondo de cuadrícula)
                images[i] = null;
                System.out.println("Fondo no encontrado: " + BACKGROUND_FILES[i]);
            }
        }
    }

    /** Retorna la imagen del fondo actual, o null si no hay */
    public BufferedImage getCurrentBackground() {
        return images[currentIndex];
    }

    /** Cambia al siguiente fondo en la lista (modo circular) */
    public void nextBackground() {
        currentIndex = (currentIndex + 1) % images.length;
    }

    /** Cambia a un fondo específico por índice */
    public void setBackground(int index) {
        if (index >= 0 && index < images.length) {
            currentIndex = index;
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}