// ── LLAMADAS A LA API DE SPRING BOOT ──
const API = 'http://localhost:8080/api';

// ── USUARIOS ──
async function apiLogin(email, password) {
    const res = await fetch(`${API}/usuarios/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    });
    if (!res.ok) throw new Error('Credenciales incorrectas');
    return res.json();
}

async function apiRegistro(datos) {
    const res = await fetch(`${API}/usuarios/registro`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(datos)
    });
    if (!res.ok) {
        const msg = await res.text();
        throw new Error(msg || 'Error al registrarse');
    }
    return res.json();
}

async function apiGetUsuario(id) {
    const res = await fetch(`${API}/usuarios/${id}`);
    if (!res.ok) throw new Error('Usuario no encontrado');
    return res.json();
}

async function apiUpdateUsuario(id, datos) {
    const res = await fetch(`${API}/usuarios/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(datos)
    });
    if (!res.ok) throw new Error('Error al actualizar');
    return res.json();
}

// ── EJERCICIOS ──
async function apiGetEjercicios() {
    const res = await fetch(`${API}/ejercicios`);
    if (!res.ok) throw new Error('Error cargando ejercicios');
    return res.json();
}

async function apiGetEjerciciosPorCategoria(categoria) {
    const res = await fetch(`${API}/ejercicios/categoria/${categoria}`);
    if (!res.ok) throw new Error('Error cargando ejercicios');
    return res.json();
}

// ── RECORDS ──
async function apiGetRecords(usuarioId) {
    const res = await fetch(`${API}/records/usuario/${usuarioId}`);
    if (!res.ok) throw new Error('Error cargando récords');
    return res.json();
}

async function apiCrearRecord(usuarioId, ejercicio, pesoMaximo, repeticiones) {
    const res = await fetch(`${API}/records`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            usuario: { id: usuarioId },
            ejercicio,
            pesoMaximo,
            repeticiones
        })
    });
    if (!res.ok) throw new Error('Error guardando récord');
    return res.json();
}

async function apiEliminarRecord(id) {
    const res = await fetch(`${API}/records/${id}`, { method: 'DELETE' });
    if (!res.ok) throw new Error('Error eliminando récord');
}
