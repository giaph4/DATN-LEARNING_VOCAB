/* Mockup behaviour: icons, drawer, dialog, toast, state switcher, API helper, study card.
   FE re-implements these as React components; the DOM/class contract stays the same. */
(() => {
  // ---- Icons (Lucide paths; FE uses lucide-react with the same names) ----
  const ICONS = {
    menu: '<path d="M4 6h16M4 12h16M4 18h16"/>',
    x: '<path d="M18 6 6 18M6 6l12 12"/>',
    home: '<path d="M3 10.5 12 3l9 7.5V21h-6v-6H9v6H3z"/>',
    'book-open': '<path d="M2 4h6a4 4 0 0 1 4 4v13a3 3 0 0 0-3-3H2zM22 4h-6a4 4 0 0 0-4 4v13a3 3 0 0 1 3-3h7z"/>',
    library: '<path d="M4 4v16M9 4v16M14 4l4 16M19 20h2"/>',
    'pencil-line': '<path d="M12 20h9M16.5 3.5a2.1 2.1 0 0 1 3 3L7 19l-4 1 1-4z"/>',
    notebook: '<path d="M6 3h12a1 1 0 0 1 1 1v16a1 1 0 0 1-1 1H6zM3 7h3M3 12h3M3 17h3M10 8h5"/>',
    'chart-line': '<path d="M3 3v18h18M7 15l4-4 3 3 5-6"/>',
    user: '<circle cx="12" cy="8" r="4"/><path d="M4 21a8 8 0 0 1 16 0"/>',
    settings: '<circle cx="12" cy="12" r="3"/><path d="M19.4 15a1.7 1.7 0 0 0 .3 1.8l.1.1a2 2 0 1 1-2.8 2.8l-.1-.1a1.7 1.7 0 0 0-1.8-.3 1.7 1.7 0 0 0-1 1.5V21a2 2 0 1 1-4 0v-.1a1.7 1.7 0 0 0-1.1-1.5 1.7 1.7 0 0 0-1.8.3l-.1.1a2 2 0 1 1-2.8-2.8l.1-.1a1.7 1.7 0 0 0 .3-1.8 1.7 1.7 0 0 0-1.5-1H3a2 2 0 1 1 0-4h.1a1.7 1.7 0 0 0 1.5-1.1 1.7 1.7 0 0 0-.3-1.8l-.1-.1a2 2 0 1 1 2.8-2.8l.1.1a1.7 1.7 0 0 0 1.8.3H9a1.7 1.7 0 0 0 1-1.5V3a2 2 0 1 1 4 0v.1a1.7 1.7 0 0 0 1 1.5 1.7 1.7 0 0 0 1.8-.3l.1-.1a2 2 0 1 1 2.8 2.8l-.1.1a1.7 1.7 0 0 0-.3 1.8V9a1.7 1.7 0 0 0 1.5 1H21a2 2 0 1 1 0 4h-.1a1.7 1.7 0 0 0-1.5 1z"/>',
    search: '<circle cx="11" cy="11" r="7"/><path d="m20 20-3.5-3.5"/>',
    'volume-2': '<path d="M11 5 6 9H2v6h4l5 4zM15.5 8.5a5 5 0 0 1 0 7M19 5a10 10 0 0 1 0 14"/>',
    check: '<path d="M20 6 9 17l-5-5"/>',
    'circle-alert': '<circle cx="12" cy="12" r="10"/><path d="M12 8v4M12 16h.01"/>',
    info: '<circle cx="12" cy="12" r="10"/><path d="M12 16v-4M12 8h.01"/>',
    'triangle-alert': '<path d="m21.7 18-8-14a2 2 0 0 0-3.4 0l-8 14A2 2 0 0 0 4 21h16a2 2 0 0 0 1.7-3zM12 9v4M12 17h.01"/>',
    'rotate-ccw': '<path d="M3 12a9 9 0 1 0 3-6.7L3 8M3 3v5h5"/>',
    'chevron-left': '<path d="m15 18-6-6 6-6"/>',
    'chevron-right': '<path d="m9 18 6-6-6-6"/>',
    'chevron-down': '<path d="m6 9 6 6 6-6"/>',
    'folder-tree': '<path d="M3 4h5l2 2h4v4H3zM3 14h5l2 2h4v4H3zM6 10v4"/>',
    'log-in': '<path d="M15 3h4a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2h-4M10 17l5-5-5-5M15 12H3"/>',
    'log-out': '<path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4M16 17l5-5-5-5M21 12H9"/>',
    image: '<rect x="3" y="3" width="18" height="18" rx="2"/><circle cx="9" cy="9" r="2"/><path d="m21 15-3.1-3.1a2 2 0 0 0-2.8 0L6 21"/>',
    monitor: '<rect x="2" y="3" width="20" height="14" rx="2"/><path d="M8 21h8M12 17v4"/>',
    smartphone: '<rect x="6" y="2" width="12" height="20" rx="2"/><path d="M11 18h2"/>',
    camera: '<path d="M14.5 4h-5L7 7H4a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2h-3z"/><circle cx="12" cy="13" r="3"/>',
    trash: '<path d="M3 6h18M8 6V4h8v2M19 6l-1 14H6L5 6"/>',
    star: '<path d="m12 3 2.8 5.7 6.2.9-4.5 4.4 1 6.2L12 17.3 6.5 20.2l1-6.2L3 9.6l6.2-.9z"/>',
    filter: '<path d="M3 5h18M6 12h12M10 19h4"/>',
    copy: '<rect x="8" y="8" width="13" height="13" rx="2"/><path d="M16 8V5a2 2 0 0 0-2-2H5a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h3"/>',
    'arrow-right': '<path d="M5 12h14M13 6l6 6-6 6"/>',
    mail: '<rect x="2" y="4" width="20" height="16" rx="2"/><path d="m22 7-10 6L2 7"/>',
    eye: '<path d="M2 12s3.5-7 10-7 10 7 10 7-3.5 7-10 7S2 12 2 12z"/><circle cx="12" cy="12" r="3"/>',
    'eye-off': '<path d="M9.9 4.2A10 10 0 0 1 12 4c6.5 0 10 8 10 8a17 17 0 0 1-2.2 3.2M6.6 6.6A17 17 0 0 0 2 12s3.5 8 10 8a9.7 9.7 0 0 0 5.4-1.6M9.9 9.9a3 3 0 0 0 4.2 4.2M2 2l20 20"/>',
  };
  // Gọi lại cho HTML chèn sau khi tải trang (danh sách dựng bằng JS)
  const renderIcons = (root = document) => root.querySelectorAll('[data-icon]').forEach((el) => {
    const name = el.dataset.icon;
    el.outerHTML = `<svg class="icon ${el.className}" viewBox="0 0 24 24" aria-hidden="true">${ICONS[name] || ''}</svg>`;
  });

  // ---- Khung người học (demo): header, menu trái, thanh dưới dựng từ MỘT cấu hình cho mọi trang ----
  // <body data-nav="hoc|thu-vien|luyen-tap|so-tay|thong-ke|ho-so|cai-dat" data-auth="required">
  const D = window.VLDemo;
  if (D && document.body.dataset.auth === 'required' && !D.account()) {
    location.replace(`${D.link('dot1/dang-nhap')}?quay-lai=${encodeURIComponent(location.pathname)}`);
  }
  const navKey = document.body.dataset.nav;
  if (D && navKey) {
    const MAIN = [
      ['hoc', 'dot2/hoc', 'book-open', 'Học hôm nay', 'Học'],
      ['thu-vien', 'dot1/thu-vien', 'library', 'Thư viện', 'Thư viện'],
      ['luyen-tap', 'dot2/luyen-tap', 'pencil-line', 'Luyện tập', 'Luyện tập'],
      ['so-tay', 'dot2/so-tay', 'notebook', 'Sổ tay', 'Sổ tay'],
      ['thong-ke', 'dot2/thong-ke', 'chart-line', 'Thống kê', null],
    ];
    const ACCOUNT = [['ho-so', 'dot1/ho-so', 'user', 'Hồ sơ'], ['cai-dat', 'dot1/cai-dat', 'settings', 'Cài đặt học']];
    const cur = (k) => (k === navKey ? ' aria-current="page"' : '');
    const item = ([k, path, icon, label]) => `<li><a href="${D.link(path)}"${cur(k)}><i data-icon="${icon}"></i>${label}</a></li>`;
    const me = D.account();
    const name = me?.displayName || '';
    const initial = (name.trim().split(/\s+/).pop() || '?').charAt(0).toUpperCase();
    const days = D.streak();
    const header = document.querySelector('.app-header');
    if (header) header.innerHTML = `
      <a class="brand" href="${D.link('dot2/hoc')}"><img class="brand-mark" src="${D.link('shared/assets/logo-mark.svg').replace(/\.svg\.html$/, '.svg')}" alt="" width="28" height="28"><span>Vocab<span class="brand-accent">Learning</span></span></a>
      <span class="header-spacer"></span>
      <div class="header-actions">
        ${days ? `<span class="streak" title="Chuỗi ngày học liên tiếp">${days} ngày</span>` : ''}
        <a class="avatar" href="${D.link('dot1/ho-so')}" id="header-avatar" aria-label="Hồ sơ của ${name}">${initial}</a>
      </div>`;
    const sidebar = document.querySelector('.sidebar');
    if (sidebar) sidebar.innerHTML = `<ul class="nav">${MAIN.map(item).join('')}</ul>
      <p class="nav-section">Tài khoản</p><ul class="nav">${ACCOUNT.map(item).join('')}</ul>`;
    const bottom = document.querySelector('.bottom-nav');
    const meKeys = ['ho-so', 'cai-dat', 'thong-ke'];
    if (bottom) bottom.innerHTML = MAIN.filter((m) => m[4]).map(([k, path, icon, , short]) => `<a href="${D.link(path)}"${cur(k)}><i data-icon="${icon}"></i>${short}</a>`).join('')
      + `<a href="${D.link('dot1/ho-so')}"${meKeys.includes(navKey) ? ' aria-current="page"' : ''}><i data-icon="user"></i>Tôi</a>`;
  }
  renderIcons();

  // ---- Image slot: có file ảnh thì hiện, chưa có thì giữ khung mô tả ----
  document.querySelectorAll('.img-slot[data-src]').forEach((slot) => {
    const img = new Image();
    img.onload = () => { img.alt = slot.dataset.alt || ''; slot.prepend(img); slot.dataset.loaded = 'true'; };
    img.src = slot.dataset.src;
  });

  // ---- Drawer (mobile nav) ----
  const sidebar = document.querySelector('.sidebar');
  const scrim = document.querySelector('.drawer-scrim');
  const toggle = document.querySelector('.menu-toggle');
  const setDrawer = (open) => {
    if (!sidebar) return;
    sidebar.dataset.open = open; scrim && (scrim.dataset.open = open);
    toggle && toggle.setAttribute('aria-expanded', open);
    if (open) sidebar.querySelector('a')?.focus();
  };
  toggle?.addEventListener('click', () => setDrawer(sidebar.dataset.open !== 'true'));
  scrim?.addEventListener('click', () => setDrawer(false));

  // ---- Dialog: [data-dialog-open="id"], [data-dialog-close] ----
  let lastFocus = null;
  const openDialog = (id) => {
    const scrimEl = document.getElementById(id); if (!scrimEl) return;
    lastFocus = document.activeElement; scrimEl.dataset.open = 'true';
    scrimEl.querySelector('button, [href], input, select, textarea')?.focus();
  };
  const closeDialog = (scrimEl) => { scrimEl.dataset.open = 'false'; lastFocus?.focus(); };
  document.addEventListener('click', (e) => {
    const opener = e.target.closest('[data-dialog-open]');
    if (opener) openDialog(opener.dataset.dialogOpen);
    const closer = e.target.closest('[data-dialog-close]');
    if (closer) closeDialog(closer.closest('.dialog-scrim'));
    if (e.target.classList?.contains('dialog-scrim')) closeDialog(e.target);
  });
  document.addEventListener('keydown', (e) => {
    if (e.key !== 'Escape') return;
    document.querySelectorAll('.dialog-scrim[data-open="true"]').forEach(closeDialog);
    setDrawer(false);
  });

  // ---- Toast (announced via aria-live) ----
  let toasts = document.querySelector('.toasts');
  if (!toasts) {
    toasts = Object.assign(document.createElement('div'), { className: 'toasts' });
    toasts.setAttribute('role', 'status'); toasts.setAttribute('aria-live', 'polite');
    document.body.append(toasts);
  }
  const toast = (message, type = 'success', ms = 4000) => {
    const icon = type === 'error' ? 'circle-alert' : type === 'info' ? 'info' : 'check';
    const el = document.createElement('div');
    el.className = `toast toast-${type}`;
    el.innerHTML = `<svg class="icon" viewBox="0 0 24 24" aria-hidden="true">${ICONS[icon]}</svg><span></span>`;
    el.querySelector('span').textContent = message;
    toasts.append(el);
    setTimeout(() => el.remove(), ms);
  };
  document.addEventListener('click', (e) => {
    const t = e.target.closest('[data-toast]'); if (t) toast(t.dataset.toast, t.dataset.toastType);
  });

  // ---- Mockup state switcher: <body data-state="success" data-states="loading empty error success"> ----
  const demo = window.VLDemo?.on ? window.VLDemo : null;
  // Chế độ demo (shared/demo.js): không có thanh trạng thái, trang chạy như "API thật" trên backend giả
  if (demo && document.body.dataset.states) document.body.dataset.state = 'live';
  const states = demo ? null : document.body.dataset.states?.split(' ');
  if (states) {
    const bar = document.createElement('div');
    bar.className = 'mock-bar'; bar.setAttribute('aria-label', 'Mockup: chọn trạng thái');
    const labels = { loading: 'Tải', empty: 'Rỗng', error: 'Lỗi', success: 'Thành công', live: 'API thật' };
    bar.innerHTML = '<span>Mockup</span>' + states.map((s) => `<button type="button" data-set-state="${s}">${labels[s] || s}</button>`).join('');
    document.body.append(bar);
    const apply = (s) => {
      document.body.dataset.state = s;
      bar.querySelectorAll('button').forEach((b) => b.setAttribute('aria-pressed', b.dataset.setState === s));
      document.dispatchEvent(new CustomEvent('mock:state', { detail: s }));
    };
    bar.addEventListener('click', (e) => { const b = e.target.closest('[data-set-state]'); if (b) apply(b.dataset.setState); });
    apply(document.body.dataset.state || states[states.length - 1]);
  }

  // ---- API helper: real backend when reachable ----
  const API_BASE = localStorage.getItem('vl.apiBase') || 'http://localhost:8080/api/v1';
  const api = async (path) => {
    if (demo) return (await demo.handle('GET', path)).body;
    const res = await fetch(API_BASE + path, { headers: { Accept: 'application/json' }, credentials: 'include' });
    const body = await res.json().catch(() => null);
    if (!res.ok) throw Object.assign(new Error(body?.detail || res.statusText), body || { status: res.status });
    return body;
  };

  // ---- Study card: Space/Enter flips, keys 1–4 rate ----
  document.querySelectorAll('[data-study]').forEach((root) => {
    const card = root.querySelector('.card');
    const flipBtn = root.querySelector('[data-flip]');
    const rating = root.querySelector('.rating');
    const flip = () => {
      card.dataset.flipped = 'true'; flipBtn.hidden = true; rating.hidden = false;
      root.querySelector('.card-back').removeAttribute('aria-hidden');
      rating.querySelector('button').focus();
    };
    flipBtn.addEventListener('click', flip);
    const rate = (btn) => {
      rating.querySelectorAll('button').forEach((b) => (b.disabled = true)); // no double submit (GT10)
      toast(`Đã ghi: ${btn.dataset.label}`, 'success', 1800);
      setTimeout(() => {
        card.dataset.flipped = 'false'; flipBtn.hidden = false; rating.hidden = true;
        rating.querySelectorAll('button').forEach((b) => (b.disabled = false));
        root.querySelector('.card-back').setAttribute('aria-hidden', 'true');
        const bar = root.querySelector('.progress > span');
        const v = Math.min(1, parseFloat(getComputedStyle(bar).getPropertyValue('--value')) + 0.05);
        bar.style.setProperty('--value', v); flipBtn.focus();
      }, 220);
    };
    rating.addEventListener('click', (e) => { const b = e.target.closest('button'); if (b) rate(b); });
    document.addEventListener('keydown', (e) => {
      if (e.target.closest('input, textarea, select')) return;
      if ((e.key === ' ' || e.key === 'Enter') && !flipBtn.hidden && document.activeElement === document.body) { e.preventDefault(); flip(); }
      if (!rating.hidden && ['1', '2', '3', '4'].includes(e.key)) rate(rating.querySelectorAll('button')[+e.key - 1]);
    });
  });

  // Ghi (POST/PUT/PATCH/DELETE): lấy CSRF một lần từ /auth/csrf, gửi kèm cookie + header X-CSRF-TOKEN
  let csrfToken = null;
  const send = async (method, path, data) => {
    if (demo) return demo.handle(method, path, data);
    if (!csrfToken) {
      const r = await fetch(API_BASE + '/auth/csrf', { credentials: 'include' });
      csrfToken = (await r.json()).token;
    }
    const res = await fetch(API_BASE + path, {
      method, credentials: 'include',
      headers: { 'Content-Type': 'application/json', Accept: 'application/json', 'X-CSRF-TOKEN': csrfToken },
      body: data === undefined ? undefined : JSON.stringify(data),
    });
    const body = await res.json().catch(() => null);
    if (!res.ok) throw Object.assign(new Error(body?.detail || res.statusText), body || { status: res.status });
    return { status: res.status, body };
  };

  // PUT tệp lên URL ký của kho (demo: lưu vào backend giả)
  const put = (intent, file) => (demo && intent.uploadUrl.startsWith('demo:')
    ? demo.upload(intent.uploadUrl, file)
    : fetch(intent.uploadUrl, { method: intent.method, headers: intent.headers, body: file }));

  window.VL = { toast, api, send, put, openDialog, renderIcons, API_BASE, demo };
})();
