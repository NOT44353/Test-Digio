@echo off
echo Setting up GitHub repository for XO Game...

echo.
echo 1. Adding remote origin...
git remote add origin https://github.com/NOT44353/Test-Digio.git

echo.
echo 2. Setting main branch...
git branch -M main

echo.
echo 3. Pushing to GitHub...
git push -u origin main

echo.
echo 4. Verifying push...
git remote -v

echo.
echo GitHub setup complete!
echo Repository URL: https://github.com/NOT44353/Test-Digio.git
echo.
echo To view your repository, visit:
echo https://github.com/NOT44353/Test-Digio
echo.
pause
