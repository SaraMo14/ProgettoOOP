package info.univpm.jobs;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import info.univpm.jobs.controller.JobController;
import info.univpm.jobs.model.JobAdv;
import info.univpm.jobs.services.GetAndDecodeJob;
import info.univpm.jobs.utils.StatsUtils;
import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;

/**
 * Classe usata per testare il metodo della classe StatsUtils. 
 * Data una serie di parole chiavi specificate dall'utente nel json body (request), restituisce true se la description contiente tali parole chiave,
 * false se non le contiene tutte.
 * @author Montese Sara
 * @author Terrenzi Riccardo
 */

public class MatchRequestTest {
	private ArrayList<String> request;
	private String description1;
	private String description2;
	@BeforeEach
	void setUp() throws Exception 
	{
		request = new ArrayList<String>();
		request.add("Python");
		request.add("Java");
		request.add("HTML");
		description1 = "<h2>Job Description:</h2>\n<p>An exciting opportunity to work at the new Engineering organization in Shell IT. We are looking for a talented Full Stack Developer with great interpersonal skills to join our team. Shell’s business has been built on a foundation of innovative technology and skilled people. By providing energy to sustain people’s lives over 125 years, Shell is one of the world’s leading companies. IT Engineering gives you an opportunity to make an impact that matters – be it in complex predictive maintenance and inventory management solutions to optimize uptime of our Upstream assets or innovative solutions that satisfy customers at one of our 49,000 branded service stations, the world’s largest retail network.</p>\n<p>Your growth is our future in IT Engineering. Information technology supports all aspects of our businesses and we have some of the world’s most complex problems to solve. You will not only get an opportunity to work with the latest technology, but with the scale of Shell, you will also get the opportunity to shape future digital technology working with the biggest players in the technology industry. With open source a key tenet of our technology choices, you will also get the opportunity to contribute back to the software community. The diversity of positions on offer are bound to help you grow in your career.</p>\n<p>Working with other computer scientists and software engineers in Shell, IT Engineering offers a unique opportunity for you to make an impact across a complex integrated energy value chain serving all lines of business in the Group. You could be creating new customer services in the largest Lubricants business globally, or innovating ways to grow an Aviation business which refuels five aircrafts every minute. With Shell investing significantly in renewables, there will be several opportunities to power our green engine too.</p>\n<p>IT Engineering drives business value through providing world class technical, functional and consulting expertise to deliver the right skills, at the right time, in the right place. In IT Engineering you will have opportunities to work on IT projects, programmes and products across the value chain of Shell. Our technical career paths will support your personal development through learning and certifications. We are the home of"
				+ " technical talent where you can grow your career, develop leading edge skills and deepen your business knowledge. Comprising of six capability centres, we bring together natural sets of skills at scale, providing our customers with size and depth in terms of business and platform knowledge. The capability centres promote continuous improvement of functional excellence, giving you the methods, standards, best practices, platforms, tools and external insights to support and accelerate your capability development. Our communities of practice provide access to expertise and learning, where your skills and passion for innovation and excellence will thrive.</p>\n<p>You are expected to be a seasoned software engineer having proven software engineering credentials delivering software products/applications generating business value. You are expected to be driven towards building quality software at pace. You would need to have a continuous improvement mindset, driving value-based process, product/application optimization.</p>\n<p>We’re looking to hone full stack developers with the ability to tackle complex problem areas in terms of people, industry and scenario. You should be able to plan, manage and execute a course of work, be highly independent, confident in your abilities and flexible enough to adapt to a fluid project landscape.</p>\n<p>You will be working closely with product owners and designers so strong knowledge of product and design will be helpful.</p>\n<h2>Key Accountabilities:</h2>\n<ul>\n<li>Solution consultation and development in accordance to the enterprise technology principles, methods, standards, and practices.</li>\n<li>Estimation, solution design, detailed technical design, application technical deliverables including code, associated tests and documentation.</li>\n<li>Contribution to functional excellence in terms of definition and maintenance of standards, methods and tools, bringing in best practices from market standard implementations.</li>\n<li>Manage complex integration scenarios and interfaces between on-premise solutions, cloud and legacy systems.</li>\n</ul>\n<h2>Skills &amp; Requirements:</h2>\n<ul>\n<li>Looking to invite passionate developers with varying software development experience ranging from 5 years to very senior profiles with 20 years’ experience, with a relevant Full Stack profile (experience in front-end, back-end, cloud automation and orchestration)</li>\n<li>Must have strong foundation in one or more of C#, Python or Java Script</li>\n<li>Proficient in Functional &amp; Object-Oriented Programming, Design Patterns with expertise in one or more of the Cloud-Native technologies (C#, Python, .NET Core, Web API, PostgreSQL, HTML5, CSS3, Golang, React.js, Node.js, Graph QL, etc).</li>\n<li>Thorough understanding of containers and functions. Deployment experience with Kubernetes (K8s) or Functions is a plus.</li>\n<li>Highly desirable to have experience deploying workloads to Azure or AWS with strong knowledge and understanding of the cloud provider’s API / associated services and infrastructure and configuration as code frameworks (Terraform &amp; Ansible)</li>\n<li>Experience using Cloud Native CI/CD tools (Azure Pipelines/Circle CI/Jenkins X) is highly desirable.</li>\n<li>Knowledge of other Cloud (Amazon Web Services, Cloudera etc), and Integration (BizTalk, Logic Apps, MuleSoft etc) technologies is highly desirable.</li>\n<li>Demonstrated ability of design and development of applications/ products utilizing a variety of architecture principles, including microservices architecture.</li>\n<li>Demonstrated application of the best practices in various stages of the software development life cycle.</li>\n<li>Experience working in agile teams with demonstrated application of the principles.</li>\n</ul>\n<p><a "
				+ "href=\"https://vonq.io/3og0XUt\">Click here for the application form!</a></p>\n";
		
		description2="<p>Zoek jij de gouden combinatie van DevOps en Security? Dan hebben wij de baan voor jou.</p>\n<p>Als "
				+ "DevSecOps engineer in ons security team werk je aan het Secure Webhosting platform, waarmee wij webapplicaties van spraakmakende klantorganisaties hosten en beveiligen. Ook voeren wij security penetration tests uit bij enkele van onze klanten.\nDaarnaast mag jij je kennis en ervaring delen als adviseur in ons Acknowledge Security Emergency Response Team. Met als doel dit nieuwe team nog succesvoller te maken.</p>\n<p>De baan</p>\n<p>Voor deze vacature komen wij graag in contact met professionals die zich herkennen in één van onderstaande omschrijvingen:</p>\n<ol>\n<li>Security specialisten die zich ook willen ontwikkelen als DevOpser;</li>\n<li>DevOpsers die zich ook willen ontwikkelen als Security specialist;</li>\n<li>Professionals die zowel van Security en DevOps verstand hebben en zich in de breedte verder willen ontwikkelen.</li>\n</ol>\n<p>Secure Webhosting platform:\nOntwikkel nieuwe diensten en automatiseer beheertaken op ons software defined platform dat wordt gemanaged met SaltStack.\nTechnologieën en systemen waar je mee werkt, zijn onder meer Elastic SIEM, ModSecurity, HA Proxy en Python op Debian Linux.\nBewaak (security) events en kom in actie als dat nodig is.\nOnderhoud de security- en hostingcomponenten van het platform.\nOndersteun webdevelopers van onze klanten in het gebruik van het platform.\nVoeg nieuwe webapplicaties aan het platform toe.</p>\n<p>Security Consultancy:\nTest de beveiliging van webapplicaties en andere soorten systemen, van netwerk tot desktop.\nGeef advies over technische beveiligingsaspecten in ICT-omgevingen van klanten.</p>\n<p>Technisch security specialist in het Acknowledge Emergency Security Response Team:\nMonitor de feeds die nieuwe vulnerabilities en exploits publiceren.\nAdviseer het security management van Acknowledge over mitigerende maatregelen.</p>\n<p>Jouw profiel</p>\n<p>Ruime ervaring met server- en netwerkbeheer is een must (Linux), ongeacht je ervaring met security of DevOps.\nErvaring of aantoonbare affiniteit met programmeren, bijv. Python en Javascript.\nErvaring of aantoonbare affiniteit met security.\nJe hebt HBO werk- en denkniveau.\nJe hebt een ICT-opleiding afgerond.\nWe zien het als een pre als je ervaring hebt met één of meer van het volgende: SaltStack, Elasticsearch, MariaDB, HA Proxy, Varnish, DRBD, WAF, IDS, Firewalling, Vulnerability Scanning, Python, Javascript en routing.</p>\n<p>Ons aanbod</p>\n<p>Salaris, dat past bij je functie en ervaring\nArbeidsvoorwaarden, zoals een aantrekkelijk pensioenregeling, korting op je zorgverzekering, goede studiekostenregeling, reiskostenvergoeding en 26 vakantiedagen\nLeren staat bij ons hoog in het vaandel, dus we bieden volop mogelijkheden om jezelf verder te ontwikkelen via trainingen, cursussen en certificeringen\nVeel vrijheid en verantwoordelijkheid om de beste resultaten te behalen en nieuwe dingen te leren\nHele fijne collega’s!\nJe bent een belangrijk onderdeel van een platte, Brabantse organisatie. Naar je ideeën wordt niet alleen geluisterd, maar er wordt ook echt iets mee gedaan\nJouw team\nSamen met drie andere collega’s vorm je een team waarbij de expertises vooral liggen op het gebied van software, security, automatisering en infrastructuur. Je krijgt veel vrijheid, zelfstandigheid en verantwoordelijkheid om je functie vorm te geven en uit te voeren. Daarnaast zijn er voldoende mogelijkheden om je te ontwikkelen binnen het team en de organisatie.\nFlexibiliteit in werkplek is de norm; jij bepaalt waar je het prettigst werkt en hoe je dat indeelt.</p>\n<p>De sollicitatieprocedure</p>\n<p>Wij houden over het algemeen de volgende sollicitatieprocedure aan. Afhankelijk van de wensen van de kandidaat of Acknowledge kunnen we hiervan afwijken. Ook houden we rekening met Corona-maatregelen en doen we zo veel mogelijk op afstand.\nTelefonische kennismaking met een van onze recruiters\nEerste sollicitatiegesprek met recruiter en teamcoach van de afdeling\nAfname van DiSC-assessment (online)\nInhoudelijke toets op skills (met name Linux en netwerk)\nTweede sollicitatiegesprek met recruiter en collega van de afdeling\nWe willen dat nieuwe collega's 100 % zeker weten dat ze de goede keuze maken. "
				+ "Het is daarom altijd mogelijk om een meeloopdag of kennismaking met de afdeling in te plannen.</p>\n"; 
	}
	@Test
	void tearDown() throws Exception {
	}

	@Test
	public void requestMatchingTest() throws Exception {
		assertEquals(true, StatsUtils.matchRequest(request, description1));
		assertEquals(false, StatsUtils.matchRequest(request, description2));
	}

}
