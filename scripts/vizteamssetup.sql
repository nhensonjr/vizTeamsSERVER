create database [vizteams-local];
GO
exec sp_configure 'contained database authentication', 1;
GO

reconfigure;
GO

use [vizteams-local];
alter database [vizteams-local]
set containment = partial;
GO

create user vizteamsuser with password = '7eTasP2BrAFE5usa';
grant CONTROL, ALTER to vizteamsuser;
EXEC sp_addrolemember 'db_ddladmin', 'vizteamsuser';
GO