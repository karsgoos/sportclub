@echo off
set cwd=%cd%

for /f %%m in ('dir /b /ad-h %~dp0') do (
  cd %~dp0%%m
  for /f %%h in ('git remote get-url heroku 2^> nul') do (
    echo:
    echo ############################################################
    echo #
    for %%* in (.) do echo # Deploying  :: %%~nx* ::  --^>  Heroku ^(%%h^)
    echo #
    echo ############################################################
    echo:
    git add --all
    git commit -m "Heroku deploy"
    git push heroku master
  )
)

cd %cwd%
