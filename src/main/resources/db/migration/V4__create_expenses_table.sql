CREATE TABLE expenses (
    id UUID PRIMARY KEY,
    amount NUMERIC(19, 2) NOT NULL,
    description TEXT,
    original_date TIMESTAMP,
    due_date TIMESTAMP,
    paid_at TIMESTAMP,
    tenant_id UUID NOT NULL,
    user_time_zone VARCHAR(50) NOT NULL DEFAULT 'UTC',
    created_at TIMESTAMP NOT NULL,
    created_by UUID NOT NULL,
    updated_at TIMESTAMP,
    updated_by UUID
);
