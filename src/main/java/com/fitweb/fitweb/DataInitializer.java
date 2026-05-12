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
                crear("Press de banca",     "fuerza",       "Pectoral",     "4×8",    "🏋️", "TAH8RxOS0VI",  List.of("Hombro","Muñeca"),       "Ejercicio básico de empuje horizontal. Mantén los omóplatos retraídos y los pies apoyados en el suelo."),
                crear("Sentadilla",         "fuerza",       "Cuádriceps",   "4×10",   "🦵", "aclHkVaku9U",  List.of("Rodilla","Cadera"),        "El rey de los ejercicios de pierna. Mantén el pecho erguido y la espalda recta."),
                crear("Peso muerto",        "fuerza",       "Espalda baja", "3×6",    "💪", "r4MzxtBKyNE",  List.of("Lumbar"),                  "Trabaja toda la cadena posterior. La barra debe mantenerse pegada al cuerpo."),
                crear("Dominadas",          "fuerza",       "Dorsal",       "3×8",    "🔄", "eGo4IYlbE5g",  List.of("Hombro","Codo"),           "Tracción vertical excelente para el dorsal ancho y bíceps."),
                crear("Press militar",      "fuerza",       "Hombro",       "3×10",   "🏋️", "mbIhJZ2Sbcc",  List.of("Hombro","Cuello"),         "Empuje vertical que trabaja deltoides y tríceps. Mantén el core tenso."),
                crear("Curl de bíceps",     "fuerza",       "Bíceps",       "3×12",   "💪", "ykJmrZ5v0Oo",  List.of("Codo","Muñeca"),           "Ejercicio de aislamiento para el bíceps. Controla la fase de bajada."),
                crear("Extensión tríceps",  "fuerza",       "Tríceps",      "3×12",   "🦾", "Zj1h0ObPsp8",  List.of("Codo"),                    "Trabaja las tres cabezas del tríceps. Mantén los codos fijos al frente."),
                crear("Remo con barra",     "fuerza",       "Dorsal",       "4×8",    "🔃", "OXH-ecu-Obw",  List.of("Lumbar"),                  "Fortalece dorsal, romboides y bíceps. Inclínate 45° con espalda recta."),
                crear("Carrera continua",   "cardio",       "Full body",    "30 min", "🏃", "_kGESn8ArrU",  List.of("Rodilla","Tobillo"),        "Cardio aeróbico ideal para resistencia. Mantén un ritmo conversacional."),
                crear("Bicicleta estática", "cardio",       "Pierna",       "20 min", "🚴", "9L2b2khySLE",  List.of("Rodilla"),                 "Cardio de bajo impacto articular. Ajusta el sillín a la altura correcta."),
                crear("Saltar comba",       "cardio",       "Full body",    "10 min", "🪢", "FJmRQ5iTXKE",  List.of("Tobillo","Rodilla"),        "Cardio de alta intensidad. Ideal como calentamiento o en circuitos HIIT."),
                crear("Burpees",            "cardio",       "Full body",    "3×10",   "⚡", "TU8QYVW0gDU",  List.of("Hombro","Rodilla"),        "Ejercicio funcional que combina fuerza y cardio. Muy efectivo para quemar calorías."),
                crear("Estiram. isquios",   "estiramiento", "Isquios",      "3×30s",  "🤸", "t19U1wN2mHE",  List.of(),                          "Mantén la espalda recta al inclinar el tronco. Ideal tras entrenar pierna."),
                crear("Movilidad cadera",   "movilidad",    "Cadera",       "3×15",   "🔵", "-1ai_Xseb20",  List.of("Cadera"),                  "Mejora el rango de movimiento coxofemoral. Fundamental para sentadillas."),
                crear("Movilidad tobillo",  "movilidad",    "Tobillo",      "2×20",   "⭕", "vd2VbfJeP64",  List.of("Tobillo"),                 "Trabaja la dorsiflexión del tobillo. Clave para buena mecánica en sentadilla."),
                crear("Rotación hombros",   "movilidad",    "Hombro",       "2×15",   "🔵", "885PHIF-ebk",  List.of(),                          "Activa la articulación glenohumeral. Perfecto como calentamiento de tren superior."),// FUERZA — Pecho
                crear("Aperturas con mancuernas", "fuerza",       "Pectoral",     "3×12",   "🏋️", "lMcLXbjrbQ4", List.of("Hombro"),              "Trabaja el pectoral en amplitud. Mantén una ligera flexión en los codos durante todo el movimiento."),
                crear("Fondos en paralelas",      "fuerza",       "Pectoral",     "3×10",   "💪", "fJ5QdPGMkiY", List.of("Hombro","Codo"),        "Ejercicio compuesto que trabaja pecho, tríceps y hombro anterior. Inclínate hacia adelante para enfatizar el pecho."),
                crear("Press inclinado",          "fuerza",       "Pectoral",     "4×8",    "🏋️", "PAd6ezGbDUQ", List.of("Hombro","Muñeca"),      "Trabaja la porción clavicular del pectoral. Usa un banco inclinado a 30-45 grados."),

// FUERZA — Espalda
                crear("Jalón al pecho",           "fuerza",       "Dorsal",       "4×10",   "🔄", "x2Y6Mb41zjY", List.of("Hombro","Codo"),        "Alternativa a las dominadas en polea. Lleva la barra hasta la parte superior del pecho."),
                crear("Remo en polea baja",       "fuerza",       "Dorsal",       "3×12",   "🔃", "duJvtG1qIko", List.of("Lumbar"),               "Trabaja dorsal y romboides. Mantén la espalda recta y los codos pegados al cuerpo."),
                crear("Encogimientos de hombros", "fuerza",       "Trapecio",     "3×15",   "💪", "Dwg9o97kUY8", List.of("Cuello"),               "Aísla el trapecio. Sube los hombros verticalmente sin rotar."),

// FUERZA — Hombro
                crear("Elevaciones laterales",    "fuerza",       "Hombro",       "3×15",   "🙆", "hgLpdwMtEEs", List.of("Hombro"),               "Aísla el deltoides medio. Sube los brazos hasta la altura del hombro con codos ligeramente flexionados."),
                crear("Elevaciones frontales",    "fuerza",       "Hombro",       "3×12",   "🙆", "O0n4ITO_288", List.of("Hombro"),               "Trabaja el deltoides anterior. Sube alternando brazos hasta la altura de los ojos."),

// FUERZA — Bíceps y Tríceps
                crear("Curl martillo",            "fuerza",       "Bíceps",       "3×12",   "💪", "j99intoPKGE", List.of("Codo","Muñeca"),        "Trabaja bíceps y braquiorradial. Agarre neutro con los pulgares hacia arriba."),
                crear("Curl en banco Scott",      "fuerza",       "Bíceps",       "3×10",   "💪", "Ks5KNBSmw6A", List.of("Codo"),                 "Aísla completamente el bíceps. El banco evita el balanceo del cuerpo."),
                crear("Press francés",            "fuerza",       "Tríceps",      "3×12",   "🦾", "Rn6LgSEPsDc", List.of("Codo"),                 "Trabaja las tres cabezas del tríceps. Mantén los codos apuntando al techo."),
                crear("Fondos en banco",          "fuerza",       "Tríceps",      "3×15",   "🦾", "bR9d6yRH3iA", List.of("Codo","Hombro"),        "Aísla el tríceps con el peso corporal. Mantén la espalda cerca del banco."),

// FUERZA — Pierna
                crear("Prensa de piernas",        "fuerza",       "Cuádriceps",   "4×10",   "🦵", "bNsrqXUIJqc", List.of("Rodilla","Cadera"),     "Alternativa a la sentadilla en máquina. Coloca los pies en la parte media de la plataforma."),
                crear("Zancadas",                 "fuerza",       "Cuádriceps",   "3×12",   "🦵", "uqvt79Uh4o4", List.of("Rodilla","Cadera"),     "Trabaja cuádriceps, glúteo e isquios. Da un paso largo y baja la rodilla trasera cerca del suelo."),
                crear("Extensión de cuádriceps",  "fuerza",       "Cuádriceps",   "3×15",   "🦵", "qw64QV9zCpc", List.of("Rodilla"),              "Aislamiento de cuádriceps en máquina. Extiende completamente la rodilla en cada repetición."),
                crear("Curl femoral",             "fuerza",       "Isquios",      "3×12",   "🦵", "VEAv16_YIF0", List.of("Rodilla"),              "Aislamiento de isquiotibiales. Curva la rodilla de forma controlada hacia los glúteos."),
                crear("Hip thrust",               "fuerza",       "Glúteo",       "4×12",   "🍑", "efe-QObKAZU", List.of("Lumbar","Cadera"),      "El mejor ejercicio para el glúteo mayor. Apoya la espalda en un banco y empuja la cadera hacia arriba."),

// FUERZA — Core
                crear("Plancha",                  "fuerza",       "Core",         "3×45s",  "🧘", "d0atctiI7Vw", List.of("Lumbar"),               "Estabilización isométrica del core. Mantén el cuerpo en línea recta sin hundir las caderas."),
                crear("Crunch abdominal",         "fuerza",       "Abdomen",      "3×20",   "🧘", "JWUyng_c-G0", List.of("Lumbar","Cuello"),      "Trabaja el recto abdominal. Eleva solo los hombros del suelo, no la espalda entera."),
                crear("Elevación de piernas",     "fuerza",       "Abdomen",      "3×15",   "🧘", "WHEppHwqVUw", List.of("Lumbar"),               "Trabaja la zona baja del abdomen. Mantén la zona lumbar pegada al suelo."),

// CARDIO
                crear("Elíptica",                 "cardio",       "Full body",    "25 min", "🏃", "Sbv44Rf-5U0", List.of("Rodilla"),              "Cardio de muy bajo impacto articular. Ideal para personas con lesiones de rodilla o cadera."),
                crear("Mountain climbers",        "cardio",       "Full body",    "3×30s",  "⚡", "FPLXxBxYcmE", List.of("Hombro","Muñeca"),      "Cardio funcional que trabaja core y resistencia. Lleva las rodillas al pecho de forma alternada y rápida."),

// MOVILIDAD Y ESTIRAMIENTO
                crear("Estiram. cuádriceps",      "estiramiento", "Cuádriceps",   "3×30s",  "🤸", "imnEsfrd8tM", List.of(),                       "De pie, lleva el talón hacia el glúteo. Mantén las rodillas juntas y el torso erguido."),
                crear("Estiram. gemelos",         "estiramiento", "Gemelos",      "3×30s",  "🤸", "zQL93JcoWWY", List.of("Tobillo"),              "Apoya las manos en la pared y estira la pierna trasera. Mantén el talón pegado al suelo."),
                crear("Movilidad torácica",       "movilidad",    "Espalda",      "2×10",   "🔵", "iWxGRsCGURM", List.of(),                       "Mejora la extensión de la columna torácica. Fundamental para press, remo y cualquier empuje overhead.")

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