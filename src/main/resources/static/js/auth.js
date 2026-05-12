// ── GESTIÓN DE SESIÓN ──
// Guarda el usuario en sessionStorage al hacer login/registro
// y lo elimina al hacer logout.
// Todas las páginas protegidas llaman a requireAuth() al cargar.

const AUTH_KEY = 'fitweb_user';

function saveUser(user) {
    sessionStorage.setItem(AUTH_KEY, JSON.stringify(user));
}

function getUser() {
    const data = sessionStorage.getItem(AUTH_KEY);
    return data ? JSON.parse(data) : null;
}

function removeUser() {
    sessionStorage.removeItem(AUTH_KEY);
}

// Redirige al login si no hay sesión activa
function requireAuth() {
    const user = getUser();
    if (!user) {
        window.location.href = '/login.html';
        return null;
    }
    return user;
}

// Cierra sesión y vuelve a la landing
function doLogout() {
    removeUser();
    window.location.href = '/index.html';
}

// Helpers compartidos
function getInitials(name) {
    return name.split(' ').slice(0, 2).map(w => w[0]).join('').toUpperCase();
}

function getIMC(peso, altura) {
    return (peso / ((altura / 100) ** 2)).toFixed(1);
}

function imcLabel(imc) {
    if (imc < 18.5) return 'Bajo peso';
    if (imc < 25)   return 'Peso normal';
    if (imc < 30)   return 'Sobrepeso';
    return 'Obesidad';
}

function imcPos(imc) {
    return Math.min(95, Math.max(5, ((imc - 15) / 25) * 100));
}

function calsTDEE(u) {
    let bmr = u.genero === 'Mujer'
        ? 10 * u.peso + 6.25 * u.altura - 5 * u.edad - 161
        : 10 * u.peso + 6.25 * u.altura - 5 * u.edad + 5;
    const factores = {
        sedentario: 1.2,
        ligero: 1.375,
        moderado: 1.55,
        activo: 1.725,
        muy_activo: 1.9
    };
    const factor = factores[u.nivelActividad] || 1.55;
    let tdee = Math.round(bmr * factor);
    if (u.objetivo === 'Perder peso') tdee -= 400;
    else if (u.objetivo === 'Ganar músculo' || u.objetivo === 'Ganar peso') tdee += 300;
    return tdee;
}

function getDia() {
    const dias = ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'];
    return dias[new Date().getDay()];
}

// Rellena el topbar con los datos del usuario
function initTopbar(activePage) {
    const user = getUser();
    if (!user) return;
    const ini = getInitials(user.nombre);
    const avatarEl = document.getElementById('user-avatar');
    if (avatarEl) avatarEl.textContent = ini;
    const pages = ['menu','ejercicios','records','chatbot','perfil'];
    pages.forEach(p => {
        const el = document.getElementById('nav-' + p);
        if (el && p === activePage) el.classList.add('active');
    });
}