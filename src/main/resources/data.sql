INSERT INTO employee(name, created_at, updated_at, delete_flag) VALUES ("煌木　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO employee(name, created_at, updated_at, delete_flag) VALUES ("田中　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO authentication(code, password, role, valid_date, employee_id) VALUES ("ktaro", "$2a$08$clh9XaYYznpX9WDqySgiCuUu4znpSeu2oJi5l2Q00UJs42Llrbd7S", "管理者", "9999-12-31", 1);
INSERT INTO authentication(code, password, role, valid_date, employee_id) VALUES ("ttaro", "$2a$10$F1k.2HZtkRpoSDymdZCTnuI7eVdoKP.Yb8gtiWmVTKejp53Htlm56", "一般", "9999-12-31", 2);
INSERT INTO report (
    report_date,
    title,
    content,
    created_at,
    updated_at,
    employee_id
) VALUES (
    '2025-10-15',
    '顧客Aとの打ち合わせ',
    '本日は顧客Aとの要件確認を実施。次回は仕様書を共有予定。',
    '2025-10-15',
    '2025-10-15',
    1
);
INSERT INTO report (
    report_date,
    title,
    content,
    created_at,
    updated_at,
    employee_id
) VALUES (
    '2025-10-14',
    'システム改修（ログイン機能）',
    'ログイン機能のバグを修正し、テストケースを追加。問題なく動作を確認。',
    '2025-10-14',
    '2025-10-14',
    2
);
INSERT INTO report (
    report_date,
    title,
    content,
    created_at,
    updated_at,
    employee_id
) VALUES (
    '2025-10-14',
    'テスト',
    'テストだよ',
    '2025-10-14',
    '2025-10-14',
    2
);
