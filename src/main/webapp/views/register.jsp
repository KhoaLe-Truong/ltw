<form action="/LTW/register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="fullname">Full Name:</label>
    <input type="text" id="fullname" name="fullname" required><br>

    <label for="images">Images (optional):</label>
    <input type="text" id="images" name="images"><br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required><br>

    <label for="roleId">Role:</label>
    <select id="roleId" name="roleId" required>
        <option value="1">boss</option>
        <option value="2" selected>manager</option>
        <option value="3">user</option>
         <option value="4">seller</option>
          <option value="5">shiper</option>
    </select><br>

    <button type="submit">Register</button>
</form>
