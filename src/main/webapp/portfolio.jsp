<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Buntu's Portfolio</title>
<style>
    body {font-family: 'Inter', 'Segoe UI', Roboto, Arial, sans-serif; font-size: 16px; margin: 40px; line-height: 154%; color: #222;}

    header { background-color: #3b66d1; /* Blue header */color: white;padding: 20px;display: flex;justify-content: space-between; align-items: center;}

    header h1 { margin: 0;font-size: 24px;}

    nav a {color: white;text-decoration: none;margin-left: 20px;font-weight: bold;}

    nav a:hover {
        text-decoration: underline;
    }

    /* Main content */
    main {max-width: 900px;margin: 40px auto;background-color: white;padding: 30px;border-radius: 12px;box-shadow: 0px 0px 15px rgba(0,0,0,0.1);}

    .intro {display: flex;align-items: center;justify-content: space-between; margin-bottom: 40px;
    }

    .intro-text {max-width: 80%;}

    .intro-text h2 {margin: 0 0 10px 0;font-size: 28px;color: #111;}

    p{text-align: justify;}

    .btn {background-color: #3b66d1;color: white;border: none;padding: 10px 20px;border-radius: 6px;cursor: pointer;font-size: 16px;
    }

    .btn:hover {
        background-color: #2e52b3;
    }

    /* Sections */
    section {margin-bottom: 30px;
    }

    section h3 {font-size: 22px;margin-bottom: 10px; }

    .logo{margin-left: 40%;  font-weight:700}
    .intro-logo{width: 150px; height: 150px; border-radius: 8px; background: linear-gradient(45deg, black, transparent); display: flex; align-items: center;}

     /* contact */
    .contactForm{display:grid;grid-template-columns:1fr 1fr;gap:10px;margin-top:12px}
     textarea{padding:10px;border-radius:8px;border:1px solid rgba(255,255,255,0.04);background: lightgrey;color:black;grid-column:1/-1;min-height:90px; font-size: 16px;}
    .inputField{padding:10px;border-radius:8px;border:1px solid rgba(255,255,255,0.04);background:lightgrey;color:black}

    footer {text-align: center;margin: 40px 0 20px 0;color: #777;}
</style>
<script>
function sendWhatsappText(e) {
  e.preventDefault();

  const phone = "0662526608";
  const message = e.target.TextMessage.value.trim();
  const yourName = e.target.yourName.value.trim();
  const yourNumber = e.target.yourNumber.value.trim();

  if (message) {
    if(isValidNumber(yourNumber)){
      alert("Not Implemented yet. Send me a Whatsapp message on "+phone);
    }else{
      alert(yourNumber+ "Is not a valid number");
    }
  } else{
    alert("Please type a message before sending.");
  }
}

function goToResumeCreatorApp(){
  window.open("http://localhost:8081/Buntu-Portfolio/ResumeCreator", '_blank');
}
function isValidNumber(value) {
  return /^[0-9]+$/.test(value);
}


</script>
</head>
<body>

<header>
    <h1>Portfolio</h1>
    <nav>
        <a href="#about">About</a>
        <a href="#projects">Projects</a>
        <a href="#contact">Contact</a>
    </nav>
</header>

<main>
    <div class="intro">
        <div class="intro-text">
            <h2>Hi, I'm Buntu</h2>
            <p>a Full Stack Developer with 4+ years of experience building scalable web applications. Check out my work or download my resume below.</p>
            <form method="POST" action="/Buntu-Portfolio/downloadMyCV" enctype="multipart/form-data">
              <button  type="submit"  class="btn">Download CV</button>
            </form>

        </div>
        <div class="intro-logo"><div class="logo">BT</div></div>
    </div>

    <section id="about">
        <h3>About Me</h3>
        <p>I'm a Full Stack Developer and DevOps enthusiast with over 4 years of experience designing, building, and maintaining robust, scalable web applications.
          I have extensive experience with Java, Spring Boot, Database admin, and modern development practices, as well as hands-on expertise in DevOps tools and workflows, including CI/CD pipelines, containerization, and automated deployment strategies.</p>
    </section>

    <section id="projects">
        <h3>Projects</h3>
        <strong>Resume Creator Web App</strong>
        <p>The Resume Creator is a web application I built to help users quickly design and generate professional resumes in PDF format. It allows users to enter their details and instantly generated a polished document that's ready to use. On the backend, the app is powered by Java and Spring Boot, Processing user details, and PDF generation. On the frontend, I kept the interface simple and user-friendly to make the process fast and intuitive. I also implemented DevOps practices such as automated builds and deployments using Jenkins, ensuring that new features and updates are delivered smoothly. This project demonstrates my ability to work across the full stack, from designing the UI, to managing server-side logic, to automating deployment. Try it out by clicking the button below to create your own resume.</p>
        <div class="projects">
          <button class="btn" onclick="goToResumeCreatorApp();">Resume Creator</button>
        </div>
    </section>

    <section id="contact">
      <div class="card" id="contact">
      <h3>Contact</h3>
      <form onsubmit="sendWhatsappText(event)" class="contactForm">
        <input placeholder="Your name" required name="yourName"  class="inputField"/>
        <input placeholder="Your number" required name="yourNumber"  class="inputField"/>
        <textarea name="TextMessage" placeholder="Write your WhatsApp message here.." required></textarea>
        <button type="submit" class="btn">Send message</button>
      </form>
    </div>
    </section>
</main>

<footer>
  2025 Buntu Tomson
</footer>

</body>
</html>