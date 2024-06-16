-- Drop the existing booking_status column
ALTER TABLE booking DROP COLUMN booking_status;

-- Add the booking_status column with ENUM type
ALTER TABLE booking ADD COLUMN booking_status ENUM('SCHEDULED', 'CANCELLED', 'CAB_ARRIVED', 'ASSIGNING_DRIVER', 'IN_RIDE', 'COMPLETED') NULL;
