/*
 * Website demo tĩnh (chỉ dành cho mockup, KHÔNG phải tính năng sản phẩm).
 * Không gọi API thật: VL.api/VL.send (app.js) trả lời từ backend giả trong file này, theo hợp đồng ở
 * report/DOT1_BAO_CAO_FE.md; các trang học/thư viện/luyện tập đọc-ghi thẳng qua VLDemo.
 * Dữ liệu nằm trong localStorage của trình duyệt; email hệ thống gửi đi hiện ở "Hộp thư demo".
 * Nạp file này TRƯỚC app.js.
 */
(() => {
  const KEY = 'vl.demo.v2';
  const DEMO_EMAIL = 'huynhgiapho@gmail.com';
  const DEMO_PASSWORD = 'huynhgiapho1234';
  const DEMO_PASSWORD_SHA = 'b38512be449bb5c8e2213040fd3f80972bf185b51eb7ec3b9503022fda659e1a';
  const MAX_IMAGE = 5 * 1024 * 1024;
  const LEVELS = ['A1', 'A2', 'B1', 'B2', 'C1', 'C2'];
  const DAY = 86400e3;

  // ---- Nội dung mẫu (chủ đề, bộ thẻ, thẻ) ----
  const TOPICS = [
    { id: '7', slug: 'am-thuc', name: 'Ẩm thực', description: 'Món ăn, nấu ăn, nhà hàng', children: [] },
    { id: '4', slug: 'cong-nghe', name: 'Công nghệ', description: 'Máy tính, phần mềm, Internet',
      children: [{ id: '9', slug: 'lap-trinh', name: 'Lập trình', description: 'Thuật ngữ lập trình cơ bản', children: [] }] },
    { id: '5', slug: 'cong-viec', name: 'Công việc', description: 'Văn phòng, họp, email', children: [] },
    { id: '2', slug: 'du-lich', name: 'Du lịch', description: 'Sân bay, khách sạn, hỏi đường',
      children: [{ id: '8', slug: 'san-bay', name: 'Sân bay', description: 'Làm thủ tục, hành lý', children: [] }] },
    { id: '1', slug: 'giao-tiep', name: 'Giao tiếp hằng ngày', description: 'Chào hỏi, mua sắm', children: [] },
    { id: '6', slug: 'hoc-thuat', name: 'Học thuật', description: 'Bài giảng, nghiên cứu', children: [] },
    { id: '3', slug: 'suc-khoe', name: 'Sức khỏe', description: 'Bệnh viện, thể thao', children: [] },
  ];
  const ALL_TOPICS = TOPICS.flatMap((t) => [t, ...t.children]);

  const c = (id, word, ipa, pos, meaning, example, vi) => ({ id, word, ipa, pos, meaning, example, vi });
  const DECKS = [
    { id: 'd1', title: 'Du lịch – sân bay', topic: '8', level: 'A2', source: 'ADMIN',
      description: 'Từ vựng khi làm thủ tục, qua an ninh và lên máy bay.', cards: [
        c('c101', 'boarding pass', '/ˈbɔː.dɪŋ ˌpɑːs/', 'danh từ', 'thẻ lên máy bay', 'Please show your boarding pass at the gate.', 'Vui lòng xuất trình thẻ lên máy bay ở cửa ra.'),
        c('c102', 'check-in counter', '/ˈtʃek.ɪn ˌkaʊn.tər/', 'danh từ', 'quầy làm thủ tục', 'The check-in counter opens two hours before departure.', 'Quầy làm thủ tục mở trước giờ khởi hành hai tiếng.'),
        c('c103', 'carry-on', '/ˈkær.i.ɒn/', 'danh từ', 'hành lý xách tay', 'Your carry-on must fit in the overhead bin.', 'Hành lý xách tay phải vừa ngăn để đồ phía trên.'),
        c('c104', 'layover', '/ˈleɪˌəʊ.vər/', 'danh từ', 'thời gian quá cảnh, dừng chờ nối chuyến', 'We had a three-hour layover in Singapore.', 'Chúng tôi quá cảnh ba tiếng ở Singapore.'),
        c('c105', 'departure lounge', '/dɪˈpɑː.tʃər ˌlaʊndʒ/', 'danh từ', 'phòng chờ khởi hành', 'Passengers are waiting in the departure lounge.', 'Hành khách đang đợi ở phòng chờ khởi hành.'),
        c('c106', 'customs', '/ˈkʌs.təmz/', 'danh từ', 'hải quan', 'You have to go through customs after landing.', 'Bạn phải qua hải quan sau khi hạ cánh.'),
        c('c107', 'delayed', '/dɪˈleɪd/', 'tính từ', 'bị hoãn, trễ giờ', 'Our flight was delayed because of the storm.', 'Chuyến bay của chúng tôi bị hoãn vì bão.'),
        c('c108', 'aisle seat', '/ˈaɪl ˌsiːt/', 'danh từ', 'ghế cạnh lối đi', 'I always ask for an aisle seat on long flights.', 'Tôi luôn xin ghế cạnh lối đi trên các chuyến bay dài.'),
      ] },
    { id: 'd2', title: 'Giao tiếp công sở hằng ngày', topic: '5', level: 'B1', source: 'ADMIN',
      description: 'Họp, trao đổi email và báo cáo tiến độ với đồng nghiệp.', cards: [
        c('c201', 'deadline', '/ˈded.laɪn/', 'danh từ', 'hạn chót', 'We need to meet the deadline on Friday.', 'Chúng ta cần kịp hạn chót vào thứ Sáu.'),
        c('c202', 'follow up', '/ˌfɒl.əʊ ˈʌp/', 'cụm động từ', 'theo dõi tiếp, liên hệ lại', "I'll follow up with the client tomorrow.", 'Mai tôi sẽ liên hệ lại với khách hàng.'),
        c('c203', 'agenda', '/əˈdʒen.də/', 'danh từ', 'chương trình, nội dung cuộc họp', "Let's go through the agenda first.", 'Hãy điểm qua nội dung cuộc họp trước.'),
        c('c204', 'postpone', '/pəʊstˈpəʊn/', 'động từ', 'hoãn lại', 'The meeting has been postponed until next week.', 'Cuộc họp đã được hoãn sang tuần sau.'),
        c('c205', 'colleague', '/ˈkɒl.iːɡ/', 'danh từ', 'đồng nghiệp', 'My colleague helped me with the report.', 'Đồng nghiệp đã giúp tôi làm báo cáo.'),
        c('c206', 'attachment', '/əˈtætʃ.mənt/', 'danh từ', 'tệp đính kèm', 'Please find the attachment below.', 'Vui lòng xem tệp đính kèm bên dưới.'),
      ] },
    { id: 'd3', title: 'IELTS Writing Task 2 – từ vựng chủ đề môi trường, biến đổi khí hậu và phát triển bền vững cho người đặt mục tiêu band 6.5 trở lên', topic: '6', level: 'B2', source: 'USER',
      description: 'Bộ tự soạn: cụm từ học thuật để viết mở bài, thân bài và kết luận cho đề môi trường.', cards: [
        c('c301', 'sustainable', '/səˈsteɪ.nə.bəl/', 'tính từ', 'bền vững; có thể duy trì lâu dài mà không gây hại cho môi trường', 'Governments should invest in sustainable energy.', 'Chính phủ nên đầu tư vào năng lượng bền vững.'),
        c('c302', 'mitigate', '/ˈmɪt.ɪ.ɡeɪt/', 'động từ', 'giảm nhẹ, làm dịu bớt', 'Planting trees can mitigate the effects of pollution.', 'Trồng cây có thể giảm nhẹ tác hại của ô nhiễm.'),
        c('c303', 'carbon footprint', '/ˌkɑː.bən ˈfʊt.prɪnt/', 'danh từ', 'lượng khí thải carbon do một người/tổ chức tạo ra', 'Cycling to work reduces your carbon footprint.', 'Đạp xe đi làm giúp giảm lượng khí thải carbon của bạn.'),
        c('c304', 'deforestation', '/diːˌfɒr.ɪˈsteɪ.ʃən/', 'danh từ', 'nạn phá rừng', 'Deforestation threatens many species.', 'Nạn phá rừng đe dọa nhiều loài.'),
        c('c305', 'renewable', '/rɪˈnjuː.ə.bəl/', 'tính từ', 'có thể tái tạo', 'Solar power is a renewable source of energy.', 'Điện mặt trời là nguồn năng lượng tái tạo.'),
        c('c306', 'detrimental', '/ˌdet.rɪˈmen.təl/', 'tính từ', 'có hại, gây tổn hại', 'Plastic waste is detrimental to marine life.', 'Rác thải nhựa gây hại cho sinh vật biển.'),
      ] },
    { id: 'd4', title: 'Lập trình cơ bản', topic: '9', level: 'B1', source: 'ADMIN',
      description: 'Thuật ngữ hay gặp khi đọc tài liệu và thảo luận kỹ thuật.', cards: [
        c('c401', 'variable', '/ˈveə.ri.ə.bəl/', 'danh từ', 'biến', 'Store the result in a variable.', 'Lưu kết quả vào một biến.'),
        c('c402', 'deploy', '/dɪˈplɔɪ/', 'động từ', 'triển khai (phần mềm)', 'We deploy the app every Friday.', 'Chúng tôi triển khai ứng dụng mỗi thứ Sáu.'),
        c('c403', 'bug', '/bʌɡ/', 'danh từ', 'lỗi phần mềm', 'I found a bug in the login page.', 'Tôi tìm thấy một lỗi ở trang đăng nhập.'),
        c('c404', 'repository', '/rɪˈpɒz.ɪ.tər.i/', 'danh từ', 'kho mã nguồn', 'Clone the repository to your computer.', 'Sao chép kho mã nguồn về máy của bạn.'),
        c('c405', 'backward compatible', '/ˌbæk.wəd kəmˈpæt.ə.bəl/', 'tính từ', 'tương thích ngược', 'The new version is backward compatible.', 'Phiên bản mới tương thích ngược.'),
      ] },
    { id: 'd5', title: 'Ẩm thực Việt – gọi món', topic: '7', level: 'A1', source: 'ADMIN',
      description: 'Gọi món, hỏi giá và mô tả hương vị ở quán ăn.', cards: [
        c('c501', 'menu', '/ˈmen.juː/', 'danh từ', 'thực đơn', 'Could I see the menu, please?', 'Cho tôi xem thực đơn được không?'),
        c('c502', 'spicy', '/ˈspaɪ.si/', 'tính từ', 'cay', 'Is this soup spicy?', 'Món súp này có cay không?'),
        c('c503', 'bill', '/bɪl/', 'danh từ', 'hóa đơn', 'Can we have the bill, please?', 'Cho chúng tôi xin hóa đơn.'),
        c('c504', 'vegetarian', '/ˌvedʒ.ɪˈteə.ri.ən/', 'tính từ', 'chay, dành cho người ăn chay', 'Do you have any vegetarian dishes?', 'Quán có món chay nào không?'),
        c('c505', 'takeaway', '/ˈteɪk.ə.weɪ/', 'danh từ', 'đồ mang đi', "I'd like it as a takeaway.", 'Tôi muốn mang về.'),
      ] },
  ];
  const CARDS = Object.fromEntries(DECKS.flatMap((d) => d.cards.map((card) => [card.id, { ...card, deckId: d.id }])));

  // ---- Lưu trữ ----
  const storage = {
    get(k) { try { return localStorage.getItem(k); } catch { return null; } },
    set(k, v) { try { localStorage.setItem(k, v); } catch { /* trình duyệt chặn: demo chỉ sống trong trang */ } },
  };
  const now = () => Date.now();
  const iso = (ms) => new Date(ms).toISOString();
  const dayKey = (ms = now()) => { const d = new Date(ms); return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')}`; };
  const rid = (n = 16) => [...crypto.getRandomValues(new Uint8Array(n / 2))].map((b) => b.toString(16).padStart(2, '0')).join('');
  const sha = async (s) => [...new Uint8Array(await crypto.subtle.digest('SHA-256', new TextEncoder().encode(s)))]
    .map((b) => b.toString(16).padStart(2, '0')).join('');
  const norm = (email) => String(email || '').trim().toLowerCase();

  const emptyLearning = () => ({ days: {}, cards: {}, studying: [], favorites: [], ownDecks: [], notebook: [], practice: [], newPerDay: 10, minutesPerDay: 15 });
  // Lịch sử 13 ngày trước (hôm nay chưa học) để trang học/thống kê có dữ liệu thật
  const seededLearning = () => {
    const l = emptyLearning();
    const pattern = [0, 18, 22, 15, 26, 31, 12, 0, 24, 20, 28, 0, 17, 23]; // chuỗi 6 ngày tính đến hôm qua
    pattern.forEach((reviews, i) => {
      if (!reviews || i === 0) return;
      l.days[dayKey(now() - i * DAY)] = { reviews, correct: Math.round(reviews * (0.72 + (i % 3) * 0.06)), newCards: Math.min(10, Math.round(reviews / 3)), minutes: Math.round(reviews * 0.6) };
    });
    l.studying = ['d1', 'd3'];
    l.favorites = ['d2'];
    l.ownDecks = ['d3'];
    // 5 thẻ đến hạn hôm nay, số còn lại của bộ đang học là thẻ mới
    ['c101', 'c102', 'c103', 'c301', 'c302'].forEach((id, i) => { l.cards[id] = { due: now() - (i + 1) * 3600e3, reps: 2 + i % 2, lapses: i === 2 ? 1 : 0 }; });
    ['c104', 'c105'].forEach((id) => { l.cards[id] = { due: now() + 3 * DAY, reps: 3, lapses: 0 }; });
    l.notebook = [
      { id: rid(8), cardId: 'c303', group: 'SPELLING', count: 3, lastAt: iso(now() - 2 * DAY), note: 'Hay viết thành "foot print" tách rời.' },
      { id: rid(8), cardId: 'c306', group: 'MEANING', count: 2, lastAt: iso(now() - DAY), note: '' },
      { id: rid(8), cardId: 'c104', group: 'LISTENING', count: 1, lastAt: iso(now() - 4 * DAY), note: '' },
    ];
    l.practice = [
      { id: rid(8), deckId: 'd1', type: 'CHOICE', total: 8, correct: 6, at: iso(now() - DAY), wrong: ['c104', 'c106'] },
      { id: rid(8), deckId: 'd3', type: 'TYPING', total: 5, correct: 3, at: iso(now() - 3 * DAY), wrong: ['c303', 'c306'] },
    ];
    return l;
  };

  const newAccount = (email, pw, displayName, extra = {}) => ({
    id: String(Math.floor(Math.random() * 9e5) + 1e5), email, pw, status: 'ACTIVE', verifiedAt: iso(now()),
    displayName, bio: null, level: 'A1', learningGoal: null, interests: [], avatarId: null, onboardingCompletedAt: null,
    google: null, sessions: [], learning: emptyLearning(), ...extra,
  });
  const fresh = () => ({
    accounts: {
      [DEMO_EMAIL]: newAccount(DEMO_EMAIL, DEMO_PASSWORD_SHA, 'Huỳnh Gia Pho', {
        id: '42', verifiedAt: iso(now() - 30 * DAY), learning: seededLearning(),
        sessions: [
          { id: '4b7e02c9d18fa663', userAgent: 'Mozilla/5.0 (iPhone; CPU iPhone OS 18_0 like Mac OS X) AppleWebKit/605.1.15 Version/18.0 Mobile/15E148 Safari/604.1',
            createdAt: iso(now() - 2 * DAY), lastActiveAt: iso(now() - 3 * 3600e3) },
          { id: 'c03a5f8e1d2b7094', userAgent: 'Mozilla/5.0 (Macintosh; Intel Mac OS X 14_5) AppleWebKit/605.1.15 Version/17.5 Safari/605.1.15',
            createdAt: iso(now() - 9 * DAY), lastActiveAt: iso(now() - 6 * DAY) },
        ],
      }),
    },
    current: null, // { email, sessionId, reauthUntil }
    tokens: {}, // token → { kind, email, exp, used }
    mails: [],
    media: {},
  });

  let db;
  try { db = JSON.parse(storage.get(KEY)); } catch { db = null; }
  if (!db?.accounts) db = fresh();
  const save = () => storage.set(KEY, JSON.stringify(db));
  save();

  // Đường dẫn: mọi trang nằm sâu 1 cấp (gd0/, dot1/, dot2/). serve (http) dùng tên không đuôi; mở file trực tiếp cần .html
  const depth = /\/(gd0|dot1|dot2)\//.test(location.pathname) ? '../' : '';
  const link = (path) => depth + (location.protocol === 'file:' ? `${path}.html` : path);

  // ---- Lỗi theo ProblemDetail của BE ----
  const TITLES = { 400: 'Dữ liệu không hợp lệ', 401: 'Chưa đăng nhập', 403: 'Không có quyền', 404: 'Không tìm thấy',
    409: 'Xung đột dữ liệu', 415: 'Định dạng không được hỗ trợ', 422: 'Không thể thực hiện' };
  const fail = (status, code, detail, fieldErrors = []) => Object.assign(new Error(detail), {
    type: `urn:vocablearning:error:${code.toLowerCase().replace(/_/g, '-')}`, title: TITLES[status], status, code, detail,
    traceId: `demo-${rid(8)}`, fieldErrors, retryable: false,
  });
  const invalid = (field, message) => fail(400, 'VALIDATION_FAILED', message, [{ field, message }]);

  const account = () => (db.current ? db.accounts[db.current.email] : null);
  const requireUser = () => {
    const a = account();
    if (!a) throw fail(401, 'UNAUTHENTICATED', 'Bạn cần đăng nhập để tiếp tục.');
    return a;
  };
  const requireReauth = () => {
    if (!db.current.reauthUntil || db.current.reauthUntil < now()) throw fail(403, 'REAUTH_REQUIRED', 'Hãy nhập lại mật khẩu để tiếp tục.');
  };

  const mail = (to, subject, text, href) => {
    db.mails.unshift({ id: rid(8), to, subject, text, link: href, at: iso(now()), read: false });
    document.dispatchEvent(new CustomEvent('demo:mail'));
  };
  const issue = (kind, email, ttlMs) => {
    Object.values(db.tokens).forEach((t) => { if (t.kind === kind && t.email === email) t.used = true; }); // mã mới thu hồi mã cũ
    const token = rid(32);
    db.tokens[token] = { kind, email, exp: now() + ttlMs, used: false };
    return token;
  };
  const takeToken = (token, kind) => {
    const t = db.tokens[token];
    if (!t || t.kind !== kind || t.used) throw fail(400, 'TOKEN_INVALID', 'Liên kết không hợp lệ hoặc đã được dùng.');
    if (t.exp < now()) throw fail(400, 'TOKEN_EXPIRED', 'Liên kết đã hết hạn. Hãy yêu cầu liên kết mới.');
    return t;
  };

  const topicRefs = (ids) => ALL_TOPICS.filter((t) => ids.includes(t.id)).sort((x, y) => x.name.localeCompare(y.name, 'vi'))
    .map(({ id, slug, name }) => ({ id, slug, name }));
  const me = (a) => {
    const avatar = a.avatarId && db.media[a.avatarId];
    return JSON.parse(JSON.stringify({
      id: a.id, email: a.email, status: a.status, roles: ['LEARNER'], emailVerified: !!a.verifiedAt,
      displayName: a.displayName, bio: a.bio ?? undefined, level: a.level, learningGoal: a.learningGoal ?? undefined,
      interests: topicRefs(a.interests),
      avatar: avatar ? { mediaId: a.avatarId, url: avatar.dataUrl, expiresAt: iso(now() + 300e3) } : undefined,
      onboardingCompletedAt: a.onboardingCompletedAt ?? undefined,
    }));
  };
  const text500 = (field, value, message) => {
    const t = String(value).trim();
    if (t.length > 500) throw invalid(field, message);
    return t || null;
  };

  function signIn(key) {
    const a = db.accounts[key];
    const sessionId = rid(16);
    a.sessions.push({ id: sessionId, userAgent: navigator.userAgent, createdAt: iso(now()), lastActiveAt: iso(now()) });
    db.current = { email: key, sessionId, reauthUntil: null };
    return { id: a.id, email: a.email, displayName: a.displayName, roles: ['LEARNER'], status: a.status };
  }

  // ---- Các endpoint của Đợt 1 (method + đường dẫn sau /api/v1) ----
  const routes = [
    ['GET', /^\/auth\/csrf$/, () => ({ token: 'demo-csrf' })],
    ['GET', /^\/topics$/, () => TOPICS],

    ['POST', /^\/auth\/register$/, async (b) => {
      const email = String(b.email || '').trim();
      const errors = [];
      if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email) || email.length > 254) errors.push({ field: 'email', message: 'Email không đúng định dạng.' });
      if (!b.password || b.password.length < 10 || b.password.length > 128) errors.push({ field: 'password', message: 'Mật khẩu phải từ 10 đến 128 ký tự.' });
      const name = String(b.displayName || '').trim();
      if (name.length < 2 || name.length > 100) errors.push({ field: 'displayName', message: 'Tên hiển thị cần từ 2 đến 100 ký tự.' });
      if (errors.length) throw fail(400, 'VALIDATION_FAILED', errors[0].message, errors);
      const key = norm(email);
      if (!db.accounts[key]) { // email đã có: vẫn trả 202 như nhau để không lộ tài khoản
        db.accounts[key] = newAccount(email, await sha(b.password), name, { status: 'PENDING', verifiedAt: null });
        const token = issue('VERIFY', key, DAY);
        mail(email, 'Xác thực email VocabLearning', `Chào ${name}, nhấn liên kết để kích hoạt tài khoản (hiệu lực 24 giờ).`,
          `${link('dot1/xac-thuc-email')}?token=${token}`);
      }
      return [202, { message: 'Nếu email hợp lệ, hãy kiểm tra hộp thư để xác thực tài khoản.' }];
    }],
    ['POST', /^\/auth\/verify-email$/, (b) => {
      const t = db.tokens[b.token];
      if (t?.used && t.kind === 'VERIFY' && db.accounts[t.email]?.verifiedAt) return { message: 'Email đã được xác thực. Bạn có thể đăng nhập.' };
      const tok = takeToken(b.token, 'VERIFY');
      const a = db.accounts[tok.email];
      tok.used = true; a.status = 'ACTIVE'; a.verifiedAt = iso(now());
      return { message: 'Email đã được xác thực. Bạn có thể đăng nhập.' };
    }],
    ['POST', /^\/auth\/resend-verification$/, (b) => {
      const a = db.accounts[norm(b.email)];
      if (a?.status === 'PENDING') {
        const token = issue('VERIFY', norm(b.email), DAY);
        mail(a.email, 'Xác thực email VocabLearning (gửi lại)', 'Liên kết mới; liên kết cũ không còn dùng được.', `${link('dot1/xac-thuc-email')}?token=${token}`);
      }
      return [202, { message: 'Nếu tài khoản đang chờ xác thực, email mới sẽ đến trong vài phút. Liên kết cũ không còn dùng được.' }];
    }],
    ['POST', /^\/auth\/login$/, async (b) => {
      const key = norm(b.email);
      const a = db.accounts[key];
      if (!a || !a.pw || a.pw !== await sha(b.password || '')) throw fail(401, 'INVALID_CREDENTIALS', 'Email hoặc mật khẩu không đúng.');
      if (a.status === 'PENDING') throw fail(403, 'EMAIL_NOT_VERIFIED', 'Email chưa được xác thực.');
      return signIn(key);
    }],
    ['POST', /^\/auth\/demo-google$/, () => {
      const key = Object.keys(db.accounts).find((k) => db.accounts[k].google);
      if (!key) throw fail(409, 'GOOGLE_EMAIL_IN_USE', 'Email Google đã có tài khoản.');
      return signIn(key);
    }],
    ['POST', /^\/auth\/logout$/, () => {
      const a = account();
      if (a) a.sessions = a.sessions.filter((s) => s.id !== db.current.sessionId);
      db.current = null;
      return [204, null];
    }],
    ['POST', /^\/auth\/forgot-password$/, (b) => {
      const a = db.accounts[norm(b.email)];
      if (a?.status === 'ACTIVE') {
        const token = issue('RESET', norm(b.email), 15 * 60e3);
        mail(a.email, 'Đặt lại mật khẩu VocabLearning', 'Liên kết có hiệu lực 15 phút. Không phải bạn yêu cầu thì bỏ qua email này.',
          `${link('dot1/dat-lai-mat-khau')}?token=${token}`);
      }
      return [202, { message: 'Nếu email thuộc một tài khoản đang hoạt động, liên kết đặt lại mật khẩu sẽ đến trong vài phút.' }];
    }],
    ['POST', /^\/auth\/reset-password$/, async (b) => {
      if (!b.newPassword || b.newPassword.length < 10 || b.newPassword.length > 128) throw invalid('newPassword', 'Mật khẩu phải từ 10 đến 128 ký tự.');
      const tok = takeToken(b.token, 'RESET');
      const a = db.accounts[tok.email];
      tok.used = true; a.pw = await sha(b.newPassword);
      a.sessions = []; // đổi mật khẩu thu hồi mọi phiên
      if (db.current?.email === tok.email) db.current = null;
      return { message: 'Đã đặt lại mật khẩu. Hãy đăng nhập bằng mật khẩu mới.' };
    }],

    ['GET', /^\/auth\/sessions$/, () => requireUser().sessions
      .map((s) => ({ ...s, current: s.id === db.current.sessionId }))
      .sort((x, y) => y.lastActiveAt.localeCompare(x.lastActiveAt))],
    ['DELETE', /^\/auth\/sessions\/([0-9a-f]+)$/, (b, [, id]) => {
      const a = requireUser();
      if (!a.sessions.some((s) => s.id === id)) throw fail(404, 'NOT_FOUND', 'Không tìm thấy phiên đăng nhập.');
      a.sessions = a.sessions.filter((s) => s.id !== id);
      if (id === db.current.sessionId) db.current = null;
      return [204, null];
    }],
    ['POST', /^\/auth\/reauthenticate$/, async (b) => {
      const a = requireUser();
      if (!a.pw) throw fail(422, 'BUSINESS_RULE_VIOLATED', 'Tài khoản chưa có mật khẩu.');
      if (a.pw !== await sha(b.password || '')) throw fail(401, 'INVALID_CREDENTIALS', 'Mật khẩu không đúng.');
      db.current.reauthUntil = now() + 300e3;
      return { reauthenticatedUntil: iso(db.current.reauthUntil) };
    }],
    ['GET', /^\/auth\/identities$/, () => {
      const a = requireUser();
      return { hasPassword: !!a.pw, identities: a.google ? [{ provider: 'google', ...a.google }] : [] };
    }],
    ['POST', /^\/auth\/identities\/google\/link$/, () => {
      const a = requireUser(); requireReauth();
      if (a.google) throw fail(409, 'CONFLICT', 'Tài khoản đã liên kết Google.');
      a.google = { email: `${a.email.split('@')[0]}@gmail.com`, linkedAt: iso(now()) };
      return { redirectUrl: `${link('dot1/bao-mat')}?lien-ket=google` }; // bản thật: /oauth2/authorization/google
    }],
    ['DELETE', /^\/auth\/identities\/google$/, () => {
      const a = requireUser(); requireReauth();
      if (!a.google) throw fail(404, 'NOT_FOUND', 'Chưa liên kết Google.');
      if (!a.pw) throw fail(422, 'LAST_LOGIN_METHOD', 'Không thể gỡ phương thức đăng nhập cuối cùng.');
      a.google = null;
      return [204, null];
    }],

    ['GET', /^\/users\/me$/, () => me(requireUser())],
    ['PATCH', /^\/users\/me\/profile$/, (b) => {
      const a = requireUser();
      const next = { ...a };
      if (b.displayName != null) {
        const n = String(b.displayName).trim();
        if (n.length < 2 || n.length > 100) throw invalid('displayName', 'Tên hiển thị cần từ 2 đến 100 ký tự.');
        next.displayName = n;
      }
      if (b.bio != null) next.bio = text500('bio', b.bio, 'Giới thiệu tối đa 500 ký tự.');
      if (b.level != null) {
        if (!LEVELS.includes(b.level)) throw invalid('level', 'Trình độ phải là một trong A1, A2, B1, B2, C1, C2.');
        next.level = b.level;
      }
      if (b.learningGoal != null) next.learningGoal = text500('learningGoal', b.learningGoal, 'Mục tiêu học tối đa 500 ký tự.');
      if (b.completeOnboarding && !a.onboardingCompletedAt) next.onboardingCompletedAt = iso(now());
      const old = a.avatarId;
      if (b.removeAvatar) next.avatarId = null;
      else if (b.avatarMediaId != null) {
        const m = db.media[b.avatarMediaId];
        if (!m || m.owner !== a.email) throw fail(404, 'NOT_FOUND', 'Không tìm thấy tệp.');
        if (m.status !== 'READY') throw fail(422, 'BUSINESS_RULE_VIOLATED', 'Ảnh chưa sẵn sàng. Hãy tải lên và xác nhận trước.');
        next.avatarId = b.avatarMediaId;
      }
      Object.assign(a, next);
      if (old && old !== a.avatarId) delete db.media[old]; // ảnh cũ không còn ai dùng
      return me(a);
    }],
    ['PUT', /^\/users\/me\/interests$/, (b) => {
      const a = requireUser();
      if (!Array.isArray(b.topicIds)) throw invalid('topicIds', 'Thiếu danh sách chủ đề.');
      const ids = [...new Set(b.topicIds.map(String))];
      if (ids.some((i) => !/^\d{1,19}$/.test(i))) throw invalid('topicIds', 'Mã chủ đề không hợp lệ.');
      if (ids.length > 20) throw invalid('topicIds', 'Chọn tối đa 20 chủ đề.');
      if (ids.some((i) => !ALL_TOPICS.some((t) => t.id === i))) {
        const m = 'Có chủ đề không còn hoạt động. Hãy tải lại danh sách chủ đề và chọn lại.';
        throw fail(422, 'BUSINESS_RULE_VIOLATED', m, [{ field: 'topicIds', message: m }]);
      }
      a.interests = ids;
      return { topics: topicRefs(ids) };
    }],

    ['POST', /^\/media\/upload-intents$/, (b) => {
      const a = requireUser();
      if (!['image/jpeg', 'image/png', 'image/webp'].includes(b.contentType)) throw fail(415, 'UNSUPPORTED_MEDIA_TYPE', 'Chỉ nhận ảnh JPEG, PNG hoặc WebP.');
      if (!(b.sizeBytes > 0) || b.sizeBytes > MAX_IMAGE) throw fail(422, 'BUSINESS_RULE_VIOLATED', 'Ảnh tối đa 5 MB.');
      const id = String(Object.keys(db.media).length + 1000 + Math.floor(Math.random() * 1000));
      db.media[id] = { owner: a.email, mimeType: b.contentType, sizeBytes: b.sizeBytes, status: 'UPLOADING', dataUrl: null };
      return [201, { mediaId: id, uploadUrl: `demo:media/${id}`, method: 'PUT', headers: { 'Content-Type': b.contentType },
        expiresAt: iso(now() + 300e3) }];
    }],
    ['POST', /^\/media\/(\d+)\/complete$/, (b, [, id]) => {
      const a = requireUser();
      const m = db.media[id];
      if (!m || m.owner !== a.email) throw fail(404, 'NOT_FOUND', 'Không tìm thấy tệp.');
      if (m.status === 'UPLOADING' && m.broken) { m.status = 'REJECTED'; throw fail(415, 'UNSUPPORTED_MEDIA_TYPE', 'Tệp không phải ảnh JPEG, PNG hoặc WebP.'); }
      if (m.status === 'UPLOADING' && !m.dataUrl) throw fail(422, 'BUSINESS_RULE_VIOLATED', 'Máy chủ chưa nhận được tệp. Hãy tải lên trước khi xác nhận.');
      if (m.status === 'REJECTED') throw fail(409, 'CONFLICT', 'Tệp này đã bị từ chối hoặc đã xóa. Hãy tải ảnh khác.');
      m.status = 'READY';
      return { id, mimeType: m.mimeType, sizeBytes: m.sizeBytes, status: 'READY' };
    }],
    ['GET', /^\/media\/(\d+)\/access$/, (b, [, id]) => {
      const a = requireUser();
      const m = db.media[id];
      if (!m || m.owner !== a.email || m.status !== 'READY') throw fail(404, 'NOT_FOUND', 'Không tìm thấy tệp.');
      return { url: m.dataUrl, expiresAt: iso(now() + 300e3) };
    }],
  ];

  const handle = async (method, path, data) => {
    await new Promise((r) => setTimeout(r, 200)); // cho thấy trạng thái đang xử lý như khi gọi mạng
    const cur = account()?.sessions.find((s) => s.id === db.current?.sessionId);
    if (cur) cur.lastActiveAt = iso(now());
    else if (db.current) db.current = null; // phiên đã bị thu hồi từ nơi khác
    for (const [m, re, fn] of routes) {
      const match = method === m && path.match(re);
      if (!match) continue;
      try {
        const out = await fn(data || {}, match);
        const [status, body] = Array.isArray(out) && typeof out[0] === 'number' ? out : [200, out];
        return { status, body };
      } finally { save(); }
    }
    throw fail(404, 'NOT_FOUND', `Demo chưa có ${method} ${path}.`);
  };

  // Thay cho PUT lên kho: thu nhỏ ảnh (≤256 px) rồi lưu dạng data URL để hiển thị lại được
  const upload = async (url, file) => {
    const m = db.media[url.split('/').pop()];
    if (!m) return { ok: false };
    try {
      const bitmap = await createImageBitmap(file);
      const scale = Math.min(1, 256 / Math.max(bitmap.width, bitmap.height));
      const canvas = Object.assign(document.createElement('canvas'), { width: Math.round(bitmap.width * scale), height: Math.round(bitmap.height * scale) });
      canvas.getContext('2d').drawImage(bitmap, 0, 0, canvas.width, canvas.height);
      m.dataUrl = canvas.toDataURL('image/jpeg', 0.85);
    } catch {
      m.broken = true; // không giải mã được: giống "tệp giả ảnh" → complete trả 415
    }
    save();
    return { ok: true };
  };

  // ---- Học tập (Đợt 2 chưa có API: trang đọc/ghi thẳng dữ liệu demo) ----
  const learning = () => account()?.learning;
  const deck = (id) => DECKS.find((d) => d.id === id);
  const today = () => learning()?.days[dayKey()] || { reviews: 0, correct: 0, newCards: 0, minutes: 0 };
  const streak = () => {
    const l = learning(); if (!l) return 0;
    let n = 0;
    for (let i = l.days[dayKey()] ? 0 : 1; l.days[dayKey(now() - i * DAY)]; i++) n++;
    return n;
  };
  // Hàng đợi hôm nay: thẻ đến hạn của các bộ đang học + thẻ mới còn trong hạn mức ngày
  const queue = (deckId) => {
    const l = learning(); if (!l) return [];
    const decks = deckId ? [deckId] : l.studying;
    const cards = decks.flatMap((id) => deck(id)?.cards || []);
    const due = cards.filter((x) => l.cards[x.id] && l.cards[x.id].due <= now());
    const room = Math.max(0, l.newPerDay - today().newCards);
    const fresh = cards.filter((x) => !l.cards[x.id]).slice(0, room);
    return [...due, ...fresh].map((x) => ({ ...CARDS[x.id], isNew: !l.cards[x.id] }));
  };
  const deckProgress = (id) => {
    const l = learning(); const d = deck(id);
    const seen = d.cards.filter((x) => l?.cards[x.id]).length;
    const due = d.cards.filter((x) => l?.cards[x.id]?.due <= now()).length;
    return { total: d.cards.length, seen, due };
  };
  const bumpDay = (patch) => {
    const l = learning(); const k = dayKey();
    const d = l.days[k] || { reviews: 0, correct: 0, newCards: 0, minutes: 0 };
    Object.entries(patch).forEach(([f, v]) => { d[f] = (d[f] || 0) + v; });
    l.days[k] = d;
  };
  const noteMistake = (cardId, group) => {
    const l = learning();
    const e = l.notebook.find((x) => x.cardId === cardId && x.group === group);
    if (e) { e.count++; e.lastAt = iso(now()); } else l.notebook.unshift({ id: rid(8), cardId, group, count: 1, lastAt: iso(now()), note: '' });
  };
  // 1 Quên, 2 Khó, 3 Nhớ, 4 Dễ — khoảng lặp đơn giản cho demo (bản thật do BE tính)
  const review = (cardId, rating) => {
    const l = learning();
    const s = l.cards[cardId] || { reps: 0, lapses: 0, due: now() };
    const isNew = !l.cards[cardId];
    const gap = { 1: 10 * 60e3, 2: DAY, 3: Math.max(1, s.reps) * 3 * DAY, 4: Math.max(1, s.reps) * 7 * DAY }[rating];
    l.cards[cardId] = { reps: rating === 1 ? 0 : s.reps + 1, lapses: s.lapses + (rating === 1 ? 1 : 0), due: now() + gap };
    bumpDay({ reviews: 1, correct: rating >= 3 ? 1 : 0, newCards: isNew ? 1 : 0 });
    if (rating === 1) noteMistake(cardId, 'MEANING');
    save();
  };

  // Sau đăng nhập: chưa thiết lập thì vào /cai-dat, xong rồi thì vào /hoc
  const home = () => link(account()?.onboardingCompletedAt ? 'dot2/hoc' : 'dot1/cai-dat');
  const reset = () => { db = fresh(); save(); location.href = link('dot1/trang-chu'); };

  // ---- Thanh demo + Hộp thư demo ----
  const esc = (s) => String(s ?? '').replace(/[&<>"]/g, (ch) => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;' }[ch]));
  const mountBar = () => {
    const bar = document.createElement('div');
    bar.className = 'mock-bar demo-bar';
    bar.setAttribute('aria-label', 'Công cụ demo');
    bar.innerHTML = `<span>Demo</span>
      <button type="button" id="demo-mail" aria-expanded="false" aria-controls="demo-inbox">Hộp thư</button>
      <button type="button" id="demo-restart">Làm lại</button>
      <a href="${depth || './'}index.html" class="demo-bar-link">Mục lục</a>`;
    const inbox = document.createElement('section');
    inbox.className = 'demo-inbox'; inbox.id = 'demo-inbox'; inbox.hidden = true;
    inbox.setAttribute('aria-label', 'Hộp thư demo');
    document.body.append(inbox, bar);

    const renderInbox = () => {
      const unread = db.mails.filter((m) => !m.read).length;
      bar.querySelector('#demo-mail').textContent = unread ? `Hộp thư (${unread})` : 'Hộp thư';
      inbox.innerHTML = `<p class="demo-inbox-head">Hộp thư demo <small>Tài khoản demo: ${DEMO_EMAIL} · ${DEMO_PASSWORD}</small></p>` + (db.mails.length
        ? db.mails.map((m) => `<article class="demo-mail" data-unread="${!m.read}">
            <p><b>${esc(m.subject)}</b></p><p class="muted">Đến: ${esc(m.to)}</p><p>${esc(m.text)}</p>
            <a class="btn btn-secondary" href="${esc(m.link)}" data-mail="${m.id}">Mở liên kết trong email</a></article>`).join('')
        : '<p class="muted">Chưa có email. Đăng ký, gửi lại xác thực hoặc quên mật khẩu sẽ gửi email vào đây.</p>');
    };
    renderInbox();
    bar.querySelector('#demo-mail').addEventListener('click', (e) => {
      inbox.hidden = !inbox.hidden; e.currentTarget.setAttribute('aria-expanded', String(!inbox.hidden));
    });
    inbox.addEventListener('click', (e) => {
      const a = e.target.closest('[data-mail]'); if (!a) return;
      const m = db.mails.find((x) => x.id === a.dataset.mail); if (m) { m.read = true; save(); }
    });
    bar.querySelector('#demo-restart').addEventListener('click', () => {
      if (confirm('Xóa toàn bộ dữ liệu demo (tài khoản mới, tiến độ học, email) và bắt đầu lại?')) reset();
    });
    document.addEventListener('demo:mail', () => { renderInbox(); window.VL?.toast('Có email mới trong Hộp thư demo', 'info'); });
  };
  if (document.readyState === 'loading') document.addEventListener('DOMContentLoaded', mountBar); else mountBar();

  window.VLDemo = {
    on: true, handle, upload, home, reset, link, save, account, learning, today, streak, queue, review, deck, deckProgress,
    bumpDay, noteMistake, dayKey, DECKS, CARDS, TOPICS, ALL_TOPICS, DEMO_EMAIL, DEMO_PASSWORD,
  };
})();
