WITH new_user AS (
    SELECT gen_random_uuid() AS id
)
INSERT INTO users (
    id,
    name,
    email,
    password,
    role,
    created_at,
    created_by,
    updated_at,
    updated_by,
    tenant_id
)
SELECT
    id,
    'Admin',
    'admin@finances.com',
    '$2a$10$7zF9v3QkGzQq9eb2EhA1AeLjLbBhuFxZh5Qj/TLkKPBgW0Zs1ltcO', -- senha: admin123 (BCrypt)
    'ADMIN',
    now(),
    id,
    null,
    null,
    gen_random_uuid()
FROM new_user;
