Descripción de la solución laboratorio 2 (Reclutamiento para Walmart)
Santiago Fuentes Rubio, Ti:1137978341
Juan Sebastian Gil Correa, Cc:1040572383

En la solución de software implementada para los procesos de reclutamiento y gestión de nómina de Walmart, se ha diseñado de una manera enfocada en que el sistema reciba hojas de vidas de las personas, y que el gestor de recursos humanos sea el usuario encargado de hacer la entrevista y llevar a cabo el proceso de ingreso de datos solicitados por Walmart.
Como supuesto acerca de la estructura de la HV, tenemos lo siguiente: la HV será tomada exclusivamente como la información personal (inclúyase nivel académico y experiencia laboral) requerida para el proceso de reclutamiento (especificado más adelante), esta información aparecerá reflejada en los atributos de las clases Person y Applicant. Cosas que normalmente aparecen en una HV como las habilidades de la persona, serán preguntadas en la entrevista y esta información será calificada de acuerdo al proceso de entrevista (especificado más adelante).
La solución se organiza en tres paquetes principales (model, service, view):
1. Walmart (Paquete view)
Es la clase principal y punto de entrada del sistema. Utiliza la librería javax.swing.JOptionPane para la interacción con el usuario. Su función es orquestar el flujo de trabajo mediante un objeto central de tipo Recruitment.
Atributos: recruitmentManager (estático, de tipo Recruitment).
Métodos principales:
main(): Inicializa el menú principal con tres módulos: Aspirantes, RRHH y Salir.

ingresarHojaDeVida(): Captura los datos básicos de la HV y crea objetos Applicant.

menuRRHH(): Submenú que permite ejecutar el filtrado, las entrevistas y la contratación.

gestionarNomina(): Implementa el polimorfismo, permitiendo asignar diferentes tipos de Contract a un Candidate y mostrar su detalle de pago mediante una única llamada a getPayrollDetail().
 
2. Person (Paquete model)
Clase base diseñada para la escalabilidad. Contiene la información mínima necesaria, permitiendo que el sistema pueda extenderse en el futuro.
·         Atributos: nombre, ciudad, edad.
3. Applicant (extends Person)
Herencia de la clase Person. Representa a cualquier individuo que entrega su HV. Introduce atributos específicos para la filtración de hoja de vida laboral.
·         Atributos: nivelAcademico (Bachiller/Universitario), experiencia (años).
4. Candidate (extends Applicant)
Representa a los aplicantes que superaron el filtro. Esta clase es el núcleo de la contratación, ya que vincula al individuo con su evaluación y su posterior contrato.
·         Atributos: contract (de tipo Contract), puntajeEntrevista y observaciones.
5. Recruitment (Paquete service)
Clase de lógica de negocio que centraliza el proceso de selección. Utiliza colecciones de tipo ArrayList para segmentar a la población.
·         Atributos: applicants, candidates, rejects y una lista de allowedCities ("bogota", "soacha", "chia"). 
·         Método filterCandidates(): Aplica las reglas de negocio de Walmart: el aspirante debe ser mayor de edad, vivir en bogota o a máximo 2 horas de bogota, poseer el nivel educativo requerido el cual es mínimo bachiller.
6. Interview (Paquete model)
Modela el proceso de evaluación cualitativa y cuantitativa. Implementa un cuestionario interactivo de preguntas que el gestor de recursos humanos registrara según lo que le responda él entrevistado.
·         Atributos: respuestas, puntajesPorPregunta, puntajeTotal y observaciones.
·         Método esApto(): Determina si el candidato es elegible para contratación basado en un umbral mínimo de 15 puntos.
 
7. Calculator (Paquete service - Interfaz)
Define el "contrato de comportamiento" para todos los cálculos financieros. Establece las constantes legales de Colombia para el año 2026, como el Salario Mínimo ($1.750.950) y el Auxilio de Transporte ($249.095).
·         Métodos: calculateGross(), calculateDeductions(), calculateNet().
8. Contract (Paquete service - Clase Abstracta)
Implementa Calculator y sirve como molde para cualquier tipo de vinculación laboral. No puede ser instanciada directamente, garantizando que no existan contratos sin una lógica de pago definida.
·         Atributos: position, baseSalary, type, fechaEmision (generada automáticamente).
·         Método abstracto: getPayrollDetail(), que obliga a las clases hijas a reportar su nómina de forma personalizada.
9. Clases de Contrato Específicas (Especialización y Polimorfismo)
Estas clases heredan de Contract y sobreescriben los métodos de cálculo para reflejar la ley vigente:
·         LaborContract: Para empleados mensuales. Aplica deducciones del 8% (salud y pensión) y suma el auxilio de transporte si el sueldo es inferior a 2 salarios mínimos.

·         ServiceContract: Para trabajadores independientes. Implementa el cálculo del IBC al 40% y aplica una retención de seguridad social del 28.5% sobre dicha base.

·         HourlyContract: Gestión de trabajo por horas. Incluye lógica compleja para Extras Diurnas (1.25), Nocturnas (1.75) y una bonificación legal de vacaciones (4.17%).
