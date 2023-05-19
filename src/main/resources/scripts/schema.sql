
create table Users(
                      userID int,
                      userType varchar(100),
                      Password varchar(100),
                      primary key(userID,userType)
);

create table Patients(
                         PatientID int not null,
                         First_Name varchar(30),
                         Last_Name varchar(30),
                         Gender varchar(5),
                         ContactNumber varchar(11),
                         Age int ,
                         EmailID varchar(30),
                         BloodGroup varchar(5),
                         Address varchar(50),
                         primary key(PatientID)
);

create table Doctors(
                        DoctorID int not null,
                        First_Name varchar(30),
                        Last_Name varchar(30),
                        Gender varchar(10),
                        ContactNumber varchar(11),
                        Age int ,
                        Entry_Charge int,#
                            Qualification varchar(50),
                        Doctor_Type varchar(50),
                        Email_Id varchar(30),
                        primary key(DoctorID)
);


create table Appointments
(
    AppointmentID int,
    Problem varchar(50),
    PatientId int,
    DoctorName varchar(20),
    DoctorID int,
    DoctorType varchar(20),
    Qualification varchar(20),
    DoctorFees int,
    PaymentStatus varchar(33),
    Appointment_Status varchar(30),
    primary key(AppointmentID),
    CONSTRAINT FK_Ap FOREIGN KEY (patientId) REFERENCES Patients(PatientID),
    CONSTRAINT FK_Adocid FOREIGN KEY (DoctorID) REFERENCES Doctors(DoctorID)
);


create table Reports
(
    ReportID int,
    appointmentID int,
    patientID int,
    DoctorID int,
    MedicinePrescribed varchar(200),
    DoctorComment varchar(200),
    primary key (ReportID),
    CONSTRAINT FK_apid FOREIGN KEY (appointmentID) REFERENCES Appointments(AppointmentID),
    CONSTRAINT FK_Rp FOREIGN KEY (patientID) REFERENCES Patients(PatientID),
    CONSTRAINT FK_Rdocid FOREIGN KEY (DoctorID) REFERENCES Doctors(DoctorID)
);


create table feedback
(
    PatientID int,
    points int,
    Doc_Nature varchar(200),
    Location varchar(200),
    PatientComment varchar(1000),
    CONSTRAINT FK_Fpid FOREIGN KEY (PatientID)
        REFERENCES Patients(PatientID)
);
