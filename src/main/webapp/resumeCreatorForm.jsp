<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Buntu Tomson — CV Form</title>
<style>
  body {font-family: Arial, sans-serif; font-size: 13px; margin: 40px; line-height: 1.5; color: #222; background-color: #f7f7f7;}

  h1, h2, h3 {margin: 0; padding: 0;}
  h1 {font-size: 28px; margin-bottom: 10px;}
  h2 {font-size: 20px; margin-top: 24px; border-bottom: 1px solid #ccc; padding-bottom: 4px;}
  h3 {font-size: 16px; margin-top: 16px;}
  .section {margin-bottom: 20px;}
  label {display: block; font-weight: bold; margin-top: 10px; margin-bottom: 4px;}
  input, textarea, select {width: 100%; padding: 8px; margin-bottom: 10px; border-radius: 4px; border: 1px solid #ccc; font-size: 14px;}
  textarea {resize: vertical;}
  button {padding: 10px 20px; font-size: 16px; border: none; border-radius: 5px; background: #007BFF; color: white; cursor: pointer;}
  button:hover {background: #0056b3;}
  main {
    max-width: 900px;
    margin: 40px auto;
    background-color: white;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
}


</style>
</head>
<body>


<main>
  <h1 style="text-align: center;">Resume Form</h1>
<form id="cvForm" action="/ResumeCreator/generatePDF">

  <div class="section">
    <h2>Personal Information</h2>
    <label for="fullName">Full Name</label>
    <input type="text" id="fullName" name="fullName" placeholder="Buntu Tomson" required>

    <label for="title">Title / Position</label>
    <input type="text" id="title" name="title" placeholder="Full-Stack Developer" required>

    <label for="email">Email</label>
    <input type="email" id="email" name="email" placeholder="example@email.com" required>

    <label for="phone">Phone</label>
    <input type="text" id="phone" name="phone" placeholder="066 252 6608" required>

    <label for="address">Address</label>
    <input type="text" id="address" name="address" placeholder="123 Example Street, Apt 456, Sample City, SC 78901, RSA" required>

    <label for="linkedin">LinkedIn</label>
    <input type="text" id="linkedin" name="linkedin" placeholder="linkedin.com/in/username" required>
  </div>

  <div class="section">
    <h2>Professional Summary</h2>
    <label for="summary">Summary</label>
    <textarea id="summary" name="summary" rows="6" placeholder="Write your professional summary here..." required></textarea>
  </div>

  <div class="section">
    <h2>Technical Skills</h2>
    <label for="skills">List your skills (comma separated)</label>
    <textarea id="skills" name="skills" rows="4" placeholder="Java, JavaScript, HTML, CSS, Spring Boot..." required></textarea>
  </div>

  <div class="section">
    <h2>Professional Experience</h2>
    <label for="experience1_title">Job Title / Role</label>
    <input type="text" id="experience1_title" name="experience1_title" placeholder="Full-Stack Developer — Company" required>

    <label for="experience1_period">Period</label>
    <input type="text" id="experience1_period" name="experience1_period" placeholder="Feb 2022 — Present" required>

    <label for="experience1_details">Responsibilities / Achievements</label>
    <textarea id="experience1_details" name="experience1_details" rows="6" placeholder="Describe your work experience here..." required></textarea>
  </div>

  <div class="section">
    <h2>Key Achievements</h2>
    <label for="achievements">Achievements</label>
    <textarea id="achievements" name="achievements" rows="6" placeholder="List key achievements..." required></textarea>
  </div>

  <div class="section">
    <h2>Education & Certifications</h2>
    <label for="education">Education / Certifications</label>
    <textarea id="education" name="education" rows="4" placeholder="Degree / Certification — Institution" required></textarea>
  </div>

  <div class="section">
    <h2>Core Competencies</h2>
    <label for="competencies">Competencies</label>
    <textarea id="competencies" name="competencies" rows="6" placeholder="Development, Database Admin, SDLC, etc." required></textarea>
  </div>

  <div class="section">
    <h2>References</h2>
    <label for="references">References</label>
    <textarea id="references" name="references" rows="4" placeholder="Name — Contact" required></textarea>
  </div>

   <div style="text-align: center;"> <button type="submit">Submit Resume</button></div>
</form>
</main>


</body>
</html>