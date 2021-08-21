package me.hideri.window;


import me.hideri.util.ClientInfo;
import me.hideri.util.ColorUtil;
import net.kodehawa.lib.imageboards.DefaultImageBoards;
import net.kodehawa.lib.imageboards.ImageBoard;
import net.kodehawa.lib.imageboards.entities.BoardImage;
import net.kodehawa.lib.imageboards.entities.Rating;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class DownloaderWindow
{
    private JFrame downloaderWindow;

    private JCheckBox checkBoxAnimated;
    private JCheckBox checkBoxFilterImages;
    private JCheckBox checkBoxUseTag;

    private JTextField textFieldTag;
    private JTextField textFieldImages;
    private JTextField textFieldStartPage;
    private JTextField textFieldEndPage;

    private JComboBox<String> comboBoxRatingSelect;
    private JComboBox<String> comboBoxBoardSelect;

    private JLabel title;
    private JLabel tag;
    private JLabel settings;
    private JLabel pages;
    private JLabel rating;
    private JLabel board;
    private JLabel images;
    private JLabel start;
    private JLabel end;
    private JLabel version;

    public List<BoardImage> tempImages;
    public BoardImage tempImage;

    private JButton tagClear;
    private JButton pageClear;
    private JButton startDownload;

    private final int width, height;
    private final int trueWidth, trueHeight;

    private boolean allowAnimated = false;
    private boolean filterImages = true;
    private boolean useTag = true;

    public DownloaderWindow()
    {
        this.width = 716;
        this.height = 539;
        this.trueWidth = this.width - 16;
        this.trueHeight = this.height - 39;
    }

    public void initDownloaderWindow()
    {
        Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();

        downloaderWindow = new JFrame("Booru Downloader");
        downloaderWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        downloaderWindow.setBounds((monitorSize.getSize().width / 2) - (trueWidth / 2), (monitorSize.getSize().height / 2) - (trueHeight / 2), width, height);
        downloaderWindow.setResizable(false);
        downloaderWindow.setLayout(null);

        downloaderWindow.getContentPane().setBackground(ColorUtil.BACKGROUND);

        /* Text Objects */
        initTitleText();
        initVersionText();
        initTagText();
        initSettingsText();
        initPagesText();
        initRatingText();
        initBoardText();
        initPageSpecifyText();

        /* CheckBox Objects */
        initAnimatedCheckBox();
        initFilterImagesCheckBox();
        initUseTagCheckBox();

        /* TextField Objects */
        initTagTextField();
        initPageTextFields();

        /* ComboBox Objects */
        initRatingSelection();
        initBoardSelection();

        /* Button Objects */
        initTagClearButton();
        initPageClearButton();
        initStartButton();

        /* Action Listeners */
        initCheckBoxActionListeners();
        initButtonActionListeners();

        downloaderWindow.setVisible(true);
    }

    private void initTitleText()
    {
        title = new JLabel("Booru Downloader", SwingConstants.CENTER);
        title.setBounds(0, 5, trueWidth - 10, 25);
        title.setFont(new Font(ClientInfo.FONT, Font.PLAIN, 20));
        title.setBackground(ColorUtil.BACKGROUND);
        title.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(title);

    }

    private void initVersionText()
    {
        version = new JLabel(ClientInfo.VER);
        version.setBounds(5, trueHeight - 15, trueWidth - 10, 12);
        version.setFont(new Font(ClientInfo.FONT, Font.PLAIN, 10));
        version.setBackground(ColorUtil.BACKGROUND);
        version.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(version);
    }

    private void initTagText()
    {
        tag = new JLabel("Tag");
        tag.setBounds(trueWidth - 40, 5, trueWidth - 10, 25);
        tag.setFont(new Font(ClientInfo.FONT, Font.PLAIN, 20));
        tag.setBackground(ColorUtil.BACKGROUND);
        tag.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(tag);

    }

    private void initSettingsText()
    {
        settings = new JLabel("Settings");
        settings.setBounds(5, 5, trueWidth - 10, 25);
        settings.setFont(new Font(ClientInfo.FONT, Font.PLAIN, 20));
        settings.setBackground(ColorUtil.BACKGROUND);
        settings.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(settings);

    }

    private void initPagesText()
    {
        pages = new JLabel("Pages");
        pages.setBounds(trueWidth - 60, 75, trueWidth - 10, 25);
        pages.setFont(new Font(ClientInfo.FONT, Font.PLAIN, 20));
        pages.setBackground(ColorUtil.BACKGROUND);
        pages.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(pages);
    }

    private void initRatingText()
    {
        rating = new JLabel("Rating");
        rating.setBounds(trueWidth - 60, 195, trueWidth - 10, 25);
        rating.setFont(new Font(ClientInfo.FONT, Font.PLAIN, 20));
        rating.setBackground(ColorUtil.BACKGROUND);
        rating.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(rating);
    }

    private void initBoardText()
    {
        board = new JLabel("Board");
        board.setBounds(trueWidth - 57, 240, trueWidth - 10, 25);
        board.setFont(new Font(ClientInfo.FONT, Font.PLAIN, 20));
        board.setBackground(ColorUtil.BACKGROUND);
        board.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(board);
    }

    private void initPageSpecifyText()
    {
        initImageCountText();
        initPageStartText();
        initPageEndText();
    }

    private void initImageCountText()
    {
        images = new JLabel("Images");
        images.setBounds(trueWidth - 95, 100, 50, 20);
        images.setFont(new Font(ClientInfo.FONT, Font.PLAIN, 14));
        images.setBackground(ColorUtil.BACKGROUND);
        images.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(images);
    }

    private void initPageStartText()
    {
        start = new JLabel("Start");
        start.setBounds(trueWidth - 80, 125, 50, 20);
        start.setFont(new Font(ClientInfo.FONT, Font.PLAIN, 14));
        start.setBackground(ColorUtil.BACKGROUND);
        start.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(start);
    }

    private void initPageEndText()
    {
        end = new JLabel("End");
        end.setBounds(trueWidth - 75, 150, 40, 20);
        end.setFont(new Font(ClientInfo.FONT, Font.PLAIN, 14));
        end.setBackground(ColorUtil.BACKGROUND);
        end.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(end);
    }

    private void initAnimatedCheckBox()
    {
        checkBoxAnimated = new JCheckBox("Allow Animated", false);
        checkBoxAnimated.setBounds(5, 30, 115, 20);
        checkBoxAnimated.setBackground(ColorUtil.BACKGROUND);
        checkBoxAnimated.setForeground(ColorUtil.TEXT);
        checkBoxAnimated.setFocusPainted(false);
        downloaderWindow.add(checkBoxAnimated);
    }

    private void initFilterImagesCheckBox()
    {
        checkBoxFilterImages = new JCheckBox("Filter Images", true);
        checkBoxFilterImages.setBounds(5, 50, 115, 20);
        checkBoxFilterImages.setBackground(ColorUtil.BACKGROUND);
        checkBoxFilterImages.setForeground(ColorUtil.TEXT);
        checkBoxFilterImages.setFocusPainted(false);
        downloaderWindow.add(checkBoxFilterImages);
    }

    private void initUseTagCheckBox()
    {
        checkBoxUseTag = new JCheckBox("Use Tag", true);
        checkBoxUseTag.setBounds(5, 70, 115, 20);
        checkBoxUseTag.setBackground(ColorUtil.BACKGROUND);
        checkBoxUseTag.setForeground(ColorUtil.TEXT);
        checkBoxUseTag.setFocusPainted(false);
        downloaderWindow.add(checkBoxUseTag);
    }

    private void initTagTextField()
    {
        textFieldTag = new JTextField();
        textFieldTag.setBounds(trueWidth - 85, 30, 80, 20);
        textFieldTag.setBackground(ColorUtil.BACKGROUND);
        textFieldTag.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(textFieldTag);
    }

    private void initPageTextFields()
    {
        initTextFieldImageCount();
        initTextFieldStartPage();
        initTextFieldEndPage();
    }

    private void initTextFieldImageCount()
    {
        textFieldImages = new JTextField("1");
        textFieldImages.setBounds(trueWidth - 45, 100, 40, 20);
        textFieldImages.setBackground(ColorUtil.BACKGROUND);
        textFieldImages.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(textFieldImages);
    }

    private void initTextFieldStartPage()
    {
        textFieldStartPage = new JTextField("0");
        textFieldStartPage.setBounds(trueWidth - 45, 125, 40, 20);
        textFieldStartPage.setBackground(ColorUtil.BACKGROUND);
        textFieldStartPage.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(textFieldStartPage);
    }

    private void initTextFieldEndPage()
    {
        textFieldEndPage = new JTextField("0");
        textFieldEndPage.setBounds(trueWidth - 45, 150, 40, 20);
        textFieldEndPage.setBackground(ColorUtil.BACKGROUND);
        textFieldEndPage.setForeground(ColorUtil.TEXT);
        downloaderWindow.add(textFieldEndPage);
    }

    private void initRatingSelection()
    {
        comboBoxRatingSelect = new JComboBox<>(new String[] { "Safe", "Questionable", "Explicit" });
        comboBoxRatingSelect.setBounds(trueWidth - 95, 220, 90, 20);
        comboBoxRatingSelect.setBackground(ColorUtil.BACKGROUND);
        comboBoxRatingSelect.setForeground(ColorUtil.TEXT);
        comboBoxRatingSelect.setLightWeightPopupEnabled(false);
        downloaderWindow.add(comboBoxRatingSelect);
    }

    private void initBoardSelection()
    {
        comboBoxBoardSelect = new JComboBox<>(new String[] { "Safebooru", "Gelbooru", "Danbooru", "Yandere", "Rule34", "E926", "E621" });
        comboBoxBoardSelect.setBounds(trueWidth - 95, 265, 90, 20);
        comboBoxBoardSelect.setBackground(ColorUtil.BACKGROUND);
        comboBoxBoardSelect.setForeground(ColorUtil.TEXT);
        comboBoxBoardSelect.setLightWeightPopupEnabled(false);
        downloaderWindow.add(comboBoxBoardSelect);
    }

    private void initTagClearButton()
    {
        tagClear = new JButton("Clear");
        tagClear.setBounds(trueWidth - 85, 55, 80, 20);
        tagClear.setBackground(ColorUtil.BACKGROUND);
        tagClear.setForeground(ColorUtil.TEXT);
        tagClear.setFocusPainted(false);
        downloaderWindow.add(tagClear);
    }

    private void initPageClearButton()
    {
        pageClear = new JButton("Clear");
        pageClear.setBounds(trueWidth - 85, 175, 80, 20);
        pageClear.setBackground(ColorUtil.BACKGROUND);
        pageClear.setForeground(ColorUtil.TEXT);
        pageClear.setFocusPainted(false);
        downloaderWindow.add(pageClear);
    }

    private void initStartButton()
    {
        startDownload = new JButton("Start");
        startDownload.setBounds(trueWidth / 2 - 35, trueHeight - 25, 70, 20);
        startDownload.setHorizontalAlignment(SwingConstants.CENTER);
        startDownload.setBackground(ColorUtil.BACKGROUND);
        startDownload.setForeground(ColorUtil.TEXT);
        startDownload.setFocusPainted(false);
        downloaderWindow.add(startDownload);
    }

    private void initCheckBoxActionListeners()
    {
        checkBoxAnimated.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                allowAnimated = checkBoxAnimated.isSelected();
            }
        });

        checkBoxFilterImages.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                filterImages = checkBoxFilterImages.isSelected();
            }
        });

        checkBoxUseTag.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                useTag = checkBoxUseTag.isSelected();
                textFieldTag.setEnabled(useTag);
                textFieldTag.setFocusable(useTag);
            }
        });
    }

    private void initButtonActionListeners()
    {
        tagClear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textFieldTag.setText("");
            }
        });

        pageClear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textFieldImages.setText("1");
                textFieldStartPage.setText("0");
                textFieldEndPage.setText("0");
            }
        });

        startDownload.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    if(Integer.parseInt(textFieldStartPage.getText()) > Integer.parseInt(textFieldEndPage.getText()))
                    {
                        System.err.println("Start was bigger than End");
                        return;
                    }

                    if(Integer.parseInt(textFieldStartPage.getText()) < 0 || Integer.parseInt(textFieldEndPage.getText()) < 0)
                    {
                        System.err.println("Start or End less than 0");
                        return;
                    }

                    if(Integer.parseInt(textFieldImages.getText()) < 1)
                    {
                        System.err.println("Image amount less than 1");
                        return;
                    }
                }
                catch(NumberFormatException ex)
                {
                    System.out.println("Start: " + textFieldStartPage.getText() + ", End: " + textFieldEndPage.getText());
                    System.err.println("Invalid number entered at start or end");
                    return;
                }

                startDownload.setVisible(false);
                startDownload.setEnabled(false);
                downloaderWindow.repaint(0, 0, width, height);

                try
                {
                    startDownloadWithParsedArguments(!useTag ? "" : textFieldTag.getText(), Objects.requireNonNull(parseBoard()), Rating.valueOf(comboBoxRatingSelect.getItemAt(comboBoxRatingSelect.getSelectedIndex()).toUpperCase()), Integer.parseInt(textFieldImages.getText()), Integer.parseInt(textFieldStartPage.getText()), Integer.parseInt(textFieldEndPage.getText()));
                }
                catch(NumberFormatException ex)
                {
                    downloaderWindow.repaint(0, 0, width, height);
                    System.out.println("Image: " + textFieldImages.getText());
                    System.err.println("Invalid number entered at images");
                }
            }
        });
    }

    private ImageBoard<?> parseBoard()
    {
        switch (comboBoxBoardSelect.getItemAt(comboBoxBoardSelect.getSelectedIndex()).toLowerCase())
        {
            case "safebooru":
            {
                return DefaultImageBoards.SAFEBOORU;
            }
            case "gelbooru":
            {
                return DefaultImageBoards.GELBOORU;
            }
            case "danbooru":
            {
                return DefaultImageBoards.DANBOORU;
            }
            case "yandere":
            {
                return DefaultImageBoards.YANDERE;
            }
            case "rule34":
            {
                return DefaultImageBoards.RULE34;
            }
            case "e926":
            {
                return DefaultImageBoards.E926;
            }
            case "e621":
            {
                return DefaultImageBoards.E621;
            }
            default:
            {
                return null;
            }
        }
    }

    private void downloadImage(String fileName, String downloadUrl)
    {
        try(InputStream in = new URL(downloadUrl).openStream())
        {
            try
            {
                Files.copy(in, Paths.get(fileName));
            }
            catch(FileAlreadyExistsException ex)
            {
                System.err.println("Image " + fileName.split("/")[fileName.split("/").length - 1] + " already exists, skipping!");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private List<BoardImage> parseImages(ImageBoard<?> imageboard, Rating rating, String tag, int limit, int page)
    {
        return (List<BoardImage>) imageboard.search(page, limit, tag, rating).blocking();
    }

    private void startDownloadWithParsedArguments(String tag, ImageBoard<?> board, Rating rating, int images, int start, int stop)
    {
        System.out.println("Downloading " + images + " image per page for " + ((stop - start) + 1) + " page(s) from " + board.getBoardType().getHost() + " with the tag [" + tag + "] and rating " + rating.getLongName());

        final File saveLocation = (!useTag || tag.equals("")) ? new File(board.getBoardType().toString().toLowerCase() + "/" + rating.getLongName() + "/generic/") : new File(board.getBoardType().toString().toLowerCase() + "/" + rating.getLongName() + "/" + tag + "/");

        if(!saveLocation.exists())
        {
            if(saveLocation.mkdirs())
            {
                System.out.println("Created Directories (" + saveLocation.getAbsolutePath() + ")");
            }
        }

        for(int i = start; i < stop + 1; i++)
        {
            List<BoardImage> parsedImages = parseImages(board, rating, tag, images, i);

            tempImages = parsedImages;

            for(BoardImage image : parsedImages)
            {
                tempImage = image;

                if(!allowAnimated && (image.getTags().contains("animated") || image.getTags().contains("video")))
                {
                    continue;
                }

                if(filterImages && filterImage(image))
                {
                    continue;
                }

                System.out.println("Downloading image " + (tempImages.indexOf(tempImage) + 1) + "/" + tempImages.size() + " (" + tempImage.getURL() + ")");

                downloadImage(saveLocation.getAbsolutePath() + "\\" + tag + "-" + image.getURL().split("/")[image.getURL().split("/").length - 1], image.getURL());
            }
        }

        System.out.println("Downloads Finished!");
        startDownload.setVisible(true);
        startDownload.setEnabled(true);
        downloaderWindow.repaint(0, 0, width, height);
    }

    public boolean filterImage(BoardImage image)
    {
        if(image.getTags().contains("loli") || image.getTags().contains("shota"))
        {
            System.out.println("Filtered image " + image.getURL());
            return true;
        }

        return false;
    }
}
