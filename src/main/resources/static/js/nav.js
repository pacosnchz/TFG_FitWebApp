function renderNav(activePage) {
    const links = [
        {href: '/menu.html',      id: 'menu',      label: 'Dashboard'},
        {href: '/ejercicios.html',id: 'ejercicios', label: 'Ejercicios'},
        {href: '/rutinas.html',   id: 'rutinas',    label: 'Rutinas'},
        {href: '/dieta.html',     id: 'dieta',      label: 'Dieta'},
        {href: '/records.html',   id: 'records',    label: 'Récords'},
        {href: '/chatbot.html',   id: 'chatbot',    label: 'IA Coach'},
        {href: '/perfil.html',    id: 'perfil',     label: 'Perfil'},
    ];

    const user = getUser();
    const initials = user ? getInitials(user.nombre) : '?';
    const nombre = user ? user.nombre.split(' ')[0] : '';

    const navHTML = `
        <div id="topbar">
            <a href="/index.html" class="logo">Fit<span>Web</span></a>
            <nav>
                ${links.map(l => `
                    <a href="${l.href}" ${l.id === activePage ? 'class="active"' : ''}>${l.label}</a>
                `).join('')}
            </nav>
            <div class="topbar-right">
                <span class="topbar-username" id="topbar-username">${nombre}</span>
                <a href="/perfil.html" class="avatar" id="user-avatar">${initials}</a>
                <button class="logout-btn" onclick="doLogout()">
                    <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><polyline points="16 17 21 12 16 7"/><line x1="21" y1="12" x2="9" y2="12"/></svg>
                    Salir
                </button>
            </div>
        </div>
    `;

    document.body.insertAdjacentHTML('afterbegin', navHTML);
}