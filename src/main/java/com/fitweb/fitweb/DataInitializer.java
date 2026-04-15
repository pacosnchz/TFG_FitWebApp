package com.fitweb.fitweb;

import com.fitweb.fitweb.model.Ejercicio;
import com.fitweb.fitweb.repository.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Override
    public void run(String... args) {
        if (ejercicioRepository.count() > 0) return;

        List<Ejercicio> ejercicios = List.of(
                crear("Press de banca",     "fuerza",       "Pectoral",     "4×8",    "🏋️", "dh3bOylv0tI",  List.of("Hombro","Muñeca"),       "Ejercicio básico de empuje horizontal. Mantén los omóplatos retraídos y los pies apoyados en el suelo."),
                crear("Sentadilla",         "fuerza",       "Cuádriceps",   "4×10",   "🦵", "aclHkVaku9U",  List.of("Rodilla","Cadera"),        "El rey de los ejercicios de pierna. Mantén el pecho erguido y la espalda recta."),
                crear("Peso muerto",        "fuerza",       "Espalda baja", "3×6",    "💪", "r4MzxtBKyNE",  List.of("Lumbar"),                  "Trabaja toda la cadena posterior. La barra debe mantenerse pegada al cuerpo."),
                crear("Dominadas",          "fuerza",       "Dorsal",       "3×8",    "🔄", "eGo4IYlbE5g",  List.of("Hombro","Codo"),           "Tracción vertical excelente para el dorsal ancho y bíceps."),
                crear("Press militar",      "fuerza",       "Hombro",       "3×10",   "🏋️", "2yjwXTZbrDM",  List.of("Hombro","Cuello"),         "Empuje vertical que trabaja deltoides y tríceps. Mantén el core tenso."),
                crear("Curl de bíceps",     "fuerza",       "Bíceps",       "3×12",   "💪", "ykJmrZ5v0Oo",  List.of("Codo","Muñeca"),           "Ejercicio de aislamiento para el bíceps. Controla la fase de bajada."),
                crear("Extensión tríceps",  "fuerza",       "Tríceps",      "3×12",   "🦾", "_gsUck-7f74",  List.of("Codo"),                    "Trabaja las tres cabezas del tríceps. Mantén los codos fijos al frente."),
                crear("Remo con barra",     "fuerza",       "Dorsal",       "4×8",    "🔃", "G8l_8chR5BE",  List.of("Lumbar"),                  "Fortalece dorsal, romboides y bíceps. Inclínate 45° con espalda recta."),
                crear("Carrera continua",   "cardio",       "Full body",    "30 min", "🏃", "_kGESn8ArrU",  List.of("Rodilla","Tobillo"),        "Cardio aeróbico ideal para resistencia. Mantén un ritmo conversacional."),
                crear("Bicicleta estática", "cardio",       "Pierna",       "20 min", "🚴", "9L2b2khySLE",  List.of("Rodilla"),                 "Cardio de bajo impacto articular. Ajusta el sillín a la altura correcta."),
                crear("Saltar comba",       "cardio",       "Full body",    "10 min", "🪢", "FJmRQ5iTXKE",  List.of("Tobillo","Rodilla"),        "Cardio de alta intensidad. Ideal como calentamiento o en circuitos HIIT."),
                crear("Burpees",            "cardio",       "Full body",    "3×10",   "⚡", "TU8QYVW0gDU",  List.of("Hombro","Rodilla"),        "Ejercicio funcional que combina fuerza y cardio. Muy efectivo para quemar calorías."),
                crear("Estiram. isquios",   "estiramiento", "Isquios",      "3×30s",  "🤸", "jOACMKBCNlk",  List.of(),                          "Mantén la espalda recta al inclinar el tronco. Ideal tras entrenar pierna."),
                crear("Estiram. hombros",   "estiramiento", "Hombro",       "3×30s",  "🙆", "_r4XBNGMXdE",  List.of(),                          "Alivia tensión en el deltoides. Imprescindible tras press o tracción."),
                crear("Estiram. lumbar",    "estiramiento", "Lumbar",       "3×45s",  "🧘", "4vDcJYejj8o",  List.of(),                          "Libera tensión lumbar. Respira profundo durante el estiramiento."),
                crear("Movilidad cadera",   "movilidad",    "Cadera",       "3×15",   "🔵", "5bFyC2AYVHQ",  List.of("Cadera"),                  "Mejora el rango de movimiento coxofemoral. Fundamental para sentadillas."),
                crear("Movilidad tobillo",  "movilidad",    "Tobillo",      "2×20",   "⭕", "4FD8-Fibf9E",  List.of("Tobillo"),                 "Trabaja la dorsiflexión del tobillo. Clave para buena mecánica en sentadilla."),
                crear("Rotación hombros",   "movilidad",    "Hombro",       "2×15",   "🔵", "eGlRiWLl_vs",  List.of(),                          "Activa la articulación glenohumeral. Perfecto como calentamiento de tren superior.")
        );

        ejercicioRepository.saveAll(ejercicios);
        System.out.println("✅ Ejercicios cargados correctamente.");
    }

    private Ejercicio crear(String nombre, String cat, String musculo, String series,
                            String icono, String yt, List<String> lesiones, String desc) {
        Ejercicio e = new Ejercicio();
        e.setNombre(nombre);
        e.setCategoria(cat);
        e.setMusculo(musculo);
        e.setSeries(series);
        e.setIcono(icono);
        e.setYoutubeid(yt);
        e.setLesionesRelacionadas(lesiones);
        e.setDescripcion(desc);
        return e;
    }
}